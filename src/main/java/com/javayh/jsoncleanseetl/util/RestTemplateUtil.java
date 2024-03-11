package com.javayh.jsoncleanseetl.util;

import java.util.Collections;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;
import com.javayh.jsoncleanseetl.config.JceRetryInterceptor;

/**
 * <p>
 * RestTemplate 自定义的配置
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024-03-07
 */
public class RestTemplateUtil {

    private static final RestTemplate REST_TEMPLATE = createRestTemplateWithRetry();

    /**
     * 添加请求重试的模板
     * @return {@link RestTemplate}
     */
    private static RestTemplate createRestTemplateWithRetry() {
        // 创建重试模板
        RetryTemplate retryTemplate = new RetryTemplate();

        // 设置重试策略，这里设置最大重试次数为3次
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);

        // 设置退避策略，这里设置固定间隔时间为1000毫秒
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(1000);

        retryTemplate.setRetryPolicy(retryPolicy);
        retryTemplate.setBackOffPolicy(backOffPolicy);

        // 创建HTTP请求工厂
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        // 设置连接超时时间
        factory.setConnectTimeout(5000);
        // 设置读取超时时间
        factory.setReadTimeout(5000);

        // 创建带有重试功能的RestTemplate
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setInterceptors(Collections.singletonList(new JceRetryInterceptor(retryTemplate)));

        return restTemplate;
    }

    public static RestTemplate getRestTemplate() {
        return REST_TEMPLATE;
    }
}
