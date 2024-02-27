package com.javayh.jsonhttpextractor.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.javayh.jsonhttpextractor.etl.DataTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 统一入口
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024-02-26
 */
@Slf4j
@RestController
@RequestMapping(value = "/transformer")
public class JsonTransformerController {

    @Autowired
    private DataTransformer dataTransformer;

    @PostMapping
    public Object transformer(@RequestBody JSONObject data, String type) {
        return dataTransformer.transform(data, type);
    }
}
