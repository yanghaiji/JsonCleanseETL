package com.javayh.jsoncleanseetl.http;

import javax.validation.constraints.NotBlank;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 自动化同步的入参
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024-02-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SyncJsonRequest extends ImportJsonRequest {
    /**
     * 请求方法
     */
    @NotBlank(message = "method is empty")
    private String method;

    /**
     * url
     */
    @NotBlank(message = "url is empty")
    private String url;

    /**
     * header
     */
    private Map<String, String> headers;

    /**
     * 请求体
     */
    private JSONObject requestBody;


}
