package com.javayh.jsoncleanseetl.http;

import javax.validation.constraints.NotBlank;

import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024-02-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportJsonRequest {

    /**
     * 配置文件的标识
     */
    @NotBlank(message = "confId is empty")
    private String confId;

    /**
     * 需要解析的源数据
     */
    private JSONObject data;

    public static ImportJsonRequest reset(SyncJsonRequest request, JSONObject reData) {
        return new ImportJsonRequest(request.getConfId(), reData);
    }
}
