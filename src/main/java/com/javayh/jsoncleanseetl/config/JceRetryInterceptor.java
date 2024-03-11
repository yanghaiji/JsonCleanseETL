package com.javayh.jsoncleanseetl.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author HaiJiYang
 */
public class JceRetryInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(JceRetryInterceptor.class);

    private final RetryTemplate retryTemplate;

    public JceRetryInterceptor(RetryTemplate retryTemplate) {
        this.retryTemplate = retryTemplate;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        String requestBody = new String(body, StandardCharsets.UTF_8);
        String requestUrl = request.getURI().toString();
        // 获取请求的路径参数
        String pathVariables = request.getURI().getPath();
        try {
            if (logger.isDebugEnabled()) {
                logger.error("Failed to send request to URL: {} , requestBody {}, pathVariables{}",
                    requestUrl, requestBody, pathVariables);
            }
            // 执行请求，如果失败会自动重试
            return retryTemplate.execute(context -> execution.execute(request, body));
        } catch (Exception e) {
            // 如果重试多次后仍然失败，这里将异常抛出
            logger.error("Failed to send request to URL: {} , requestBody {}, pathVariables{} , Exception {}",
                requestUrl, requestBody, pathVariables, e.getMessage(), e);
            throw e;
        }
    }
}
