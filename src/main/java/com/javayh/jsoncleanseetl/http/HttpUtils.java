package com.javayh.jsoncleanseetl.http;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * com.javayh.jsoncleanseetl.http util 工具
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024/2/27
 */
public class HttpUtils {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    /**
     * 统一的 http工具
     *
     * @param request {@link SyncJsonRequest}请求参数封装提
     */
    public static JSONObject sendRequest(SyncJsonRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Map<String, String> headers = request.getHeaders();
        if (headers != null) {
            httpHeaders.setAll(headers);
        }
        HttpMethod method = HttpMethod.resolve(request.getMethod());
        HttpEntity<Object> httpEntity = new HttpEntity<>(request.getRequestBody(), httpHeaders);

        return JSONObject.parseObject(REST_TEMPLATE.exchange(request.getUrl(), method, httpEntity, String.class).getBody());
    }
}
