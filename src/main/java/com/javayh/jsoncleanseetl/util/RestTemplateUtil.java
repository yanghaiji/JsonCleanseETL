package com.javayh.jsoncleanseetl.util;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

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

    private static final RestTemplate REST_TEMPLATE;

    static {
        REST_TEMPLATE = new RestTemplate(createClientHttpRequestFactory());
    }

    private static ClientHttpRequestFactory createClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        // 设置连接超时时间
        factory.setConnectTimeout(5000);
        // 设置读取超时时间
        factory.setReadTimeout(5000);
        return factory;
    }

    public static RestTemplate getRestTemplate() {
        return REST_TEMPLATE;
    }
}
