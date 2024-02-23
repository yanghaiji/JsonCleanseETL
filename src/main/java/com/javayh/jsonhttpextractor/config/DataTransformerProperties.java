package com.javayh.jsonhttpextractor.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024-02-22
 */
@Data
@ConfigurationProperties(prefix = "json.mapping")
public class DataTransformerProperties {

    /**
     * json的描述
     */
    private String title;

    /**
     * 数据载体
     */
    private Map<String, String> paths;

}

