package com.javayh.jsoncleanseetl.file;

import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;

/**
 * @author HaiJiYang
 */
@Data
public class FileAndJsonRequest {

    /**
     * 文件上传
     */
    private MultipartFile file;

    /**
     * 带解析的数据
     */
    private String data;

}