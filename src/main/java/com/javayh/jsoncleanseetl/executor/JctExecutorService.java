package com.javayh.jsoncleanseetl.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 线程池
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024-03-03
 */
public class JctExecutorService {

    private static final int CORE = Runtime.getRuntime().availableProcessors();
    public static final int TIMEOUT = 60;

    private volatile static ThreadPoolExecutor singleton;

    private JctExecutorService() {
    }

    /**
     * <p>
     * 创建线程池
     * </p>
     *
     * @return java.util.concurrent.ThreadPoolExecutor
     * @version 1.0.0
     * @author hai ji
     * @since 2024-03-03
     */
    public static ThreadPoolExecutor executor() {
        if (singleton == null) {
            synchronized (JctExecutorService.class) {
                if (singleton == null) {
                    singleton = new ThreadPoolExecutor(CORE, CORE + 1, 0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return singleton;
    }

}
