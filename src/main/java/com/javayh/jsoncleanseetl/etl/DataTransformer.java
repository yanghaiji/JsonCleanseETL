package com.javayh.jsoncleanseetl.etl;

import com.alibaba.fastjson.JSONObject;
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
}
