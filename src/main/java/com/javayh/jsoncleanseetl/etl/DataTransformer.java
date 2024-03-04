package com.javayh.jsoncleanseetl.etl;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.javayh.jsoncleanseetl.exception.JsonCleanseEtlException;
import com.javayh.jsoncleanseetl.executor.JctExecutorService;
import com.javayh.jsoncleanseetl.http.ImportJsonRequest;
import com.javayh.jsoncleanseetl.http.SyncJsonRequest;

/**
 * <p>
 * json httpEtl
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024-02-22
 */
public interface DataTransformer {

    Logger logger = LoggerFactory.getLogger(DataTransformer.class);

    /**
     * <p>
     * 数据提取，主要用于http/https/或者上传的数据的提取
     * </p>
     *
     * @param data 元数据
     * @return T 返回值的泛型
     * @version 1.0.0
     * @author hai ji
     * @since 2024/2/22
     */
    <T> T extract(SyncJsonRequest data);

    /**
     * <p>
     * 数据转换，将上一步提取的数据，进行转换
     * </p>
     *
     * @param data 元数据
     * @return T
     * @version 1.0.0
     * @author hai ji
     * @since 2024/2/22
     */
    <T> T transform(ImportJsonRequest data);

    /**
     * 模板调用
     *
     * @param data 调用数据
     * @param <T>  http 同步策略参数
     * @return
     */
    default <T> T httpEtl(SyncJsonRequest data) {
        JSONObject extract = extract(data);
        return transform(ImportJsonRequest.reset(data, extract));
    }


    /**
     * 模板调用,多个流水的方式
     *
     * @param data 调用数据
     */
    default void httpEtlExecutor(List<SyncJsonRequest> data) {
        ThreadPoolExecutor executor = JctExecutorService.executor();
        int size = data.size();
        CountDownLatch latch = new CountDownLatch(size);
        try {
            data.forEach(o -> executor.execute(() -> {
                try {
                    httpEtl(o);
                } catch (Exception e) {
                    logger.error("httpEtl 异常 {}", e.getMessage(), e);
                    throw new JsonCleanseEtlException(e);
                } finally {
                    latch.countDown();
                }
            }));
        } finally {
            try {
                latch.await();
                executor.shutdown();
                // 等待最多60秒，让线程池中的任务完成
                if (!executor.awaitTermination(JctExecutorService.TIMEOUT, TimeUnit.SECONDS)) {
                    // 如果任务没有在指定时间内完成，则强制关闭线程池
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                // 当前线程被中断，重新尝试关闭线程池
                executor.shutdownNow();
                logger.error("httpEtl 线程通信异常 {}", e.getMessage(), e);
            }
        }
    }
}
