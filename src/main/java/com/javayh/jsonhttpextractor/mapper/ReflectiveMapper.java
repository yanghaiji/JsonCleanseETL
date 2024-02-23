package com.javayh.jsonhttpextractor.mapper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.javayh.jsonhttpextractor.config.DataTransformerProperties;
import com.jayway.jsonpath.JsonPath;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 数据映射器
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024-02-22
 */
@Slf4j
@Component
public class ReflectiveMapper {

    private final DataTransformerProperties jsonPathConfig;

    public ReflectiveMapper(DataTransformerProperties jsonPathConfig) {
        this.jsonPathConfig = jsonPathConfig;
    }

    public <T> T map(JSONObject jsonObject, Class<T> targetType) {
        if (targetType.isAssignableFrom(HashMap.class) || targetType.isAssignableFrom(JSONObject.class)) {
            return map(jsonObject);
        }
        return mapToObject(jsonObject, targetType);
    }

    private <T> T map(JSONObject jsonObject) {
        Map<String, Object> obj = new HashMap<>();
        for (Map.Entry<String, String> entry : jsonPathConfig.getPaths().entrySet()) {
            String fieldName = entry.getKey();
            String jsonPath = entry.getValue();
            Object fieldValue = readFieldValue(jsonObject, jsonPath);
            obj.put(fieldName, fieldValue);
        }
        return (T) obj;
    }

    private <T> T mapToObject(JSONObject jsonObject, Class<T> targetType) {
        T obj = null;
        try {
            obj = targetType.getDeclaredConstructor().newInstance();
            for (Field field : targetType.getDeclaredFields()) {
                String fieldName = field.getName();
                Object fieldValue = jsonObject.get(fieldName);
                if (fieldValue != null) {
                    field.setAccessible(true);
                    field.set(obj, fieldValue);
                }
            }
        } catch (Exception e) {
            log.error("数据转换异常{}", e.getMessage(), e);
        }
        return obj;
    }

    private Object readFieldValue(JSONObject jsonObject, String jsonPath) {
        Object fieldValue = JsonPath.read(jsonObject, jsonPath);
        return Objects.isNull(fieldValue) ? "" : fieldValue;
    }
}
