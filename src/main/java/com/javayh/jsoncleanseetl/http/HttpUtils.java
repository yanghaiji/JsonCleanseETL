package com.javayh.jsoncleanseetl.http;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import com.alibaba.fastjson.JSONObject;
import com.javayh.jsoncleanseetl.util.RestTemplateUtil;

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

        return JSONObject.parseObject(RestTemplateUtil.getRestTemplate()
            .exchange(request.getUrl(), method, httpEntity, String.class).getBody());
    }
}
