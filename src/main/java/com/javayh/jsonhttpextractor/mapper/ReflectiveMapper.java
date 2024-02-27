package com.javayh.jsonhttpextractor.mapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import com.alibaba.fastjson.JSONObject;
import com.javayh.jsonhttpextractor.config.DataTransformerProperties;
import com.javayh.jsonhttpextractor.config.JsonMappingProperties;
import com.javayh.jsonhttpextractor.exception.JsonConfigException;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

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

    public <T> JSONObject transformer(JSONObject entity, String type) {
        Optional<DataTransformerProperties.TransformConfig> first = jsonPathConfig.getTransforms().stream()
            .filter(transformConfig -> transformConfig.getType().equals(type)).findFirst();
        if (first.isPresent()) {
            DataTransformerProperties.TransformConfig transformConfig = first.get();
            return mapObject(entity, transformConfig.getMappings());
        }
        throw new JsonConfigException(type + "mapping configuration missing; please check your " +
            "dataTransformerProperties configuration.");
    }

    /**
     * <p>
     *
     * </p>
     *
     * @param entity       源数据
     * @param jsonMappings 具体转换的配置规则，参考{@link JsonMappingProperties}
     * @return com.alibaba.fastjson.JSONObject
     * @version 1.0.0
     * @author hai ji
     * @since 2024/2/26
     */
    private JSONObject mapObject(JSONObject entity, List<JsonMappingProperties> jsonMappings) {
        JSONObject jsonObject = new JSONObject();
        if (CollectionUtils.isEmpty(jsonMappings)) {
            return jsonObject;
        }
        jsonMappings.forEach(jsonMapping -> {
            String jsonPath = jsonMapping.getPath();
            String targetName = jsonMapping.getTargetName();
            Object fieldValue = readFieldValue(entity, jsonPath);
            if (fieldValue != null) {
                jsonObject.put(targetName, fieldValue);
            }

        });
        return jsonObject;
    }

    private Object readFieldValue(JSONObject jsonObject, String jsonPath) {
        try {
            Object fieldValue = JsonPath.read(jsonObject, jsonPath);
            return Objects.isNull(fieldValue) ? "" : fieldValue;
        } catch (Exception e) {
            throw new PathNotFoundException(e.getMessage());
        }
    }

}
