package com.javayh.jsoncleanseetl.mapper;

import java.util.List;
import java.util.Objects;

import org.springframework.util.CollectionUtils;
import com.alibaba.fastjson.JSONObject;
import com.javayh.jsoncleanseetl.config.JsonMappingProperties;
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
public class ReflectiveMapper {

    /**
     * <p>
     * 数据结构转换
     * </p>
     *
     * @param entity       源数据
     * @param jsonMappings 具体转换的配置规则，参考{@link JsonMappingProperties}
     * @return com.alibaba.fastjson.JSONObject
     * @version 1.0.0
     * @author hai ji
     * @since 2024/2/26
     */
    public static JSONObject mapper(JSONObject entity, List<JsonMappingProperties> jsonMappings) {
        JSONObject data = new JSONObject();
        if (CollectionUtils.isEmpty(jsonMappings)) {
            return data;
        }
        jsonMappings.forEach(jsonMapping -> {
            String jsonPath = jsonMapping.getPath();
            String targetName = jsonMapping.getTargetName();
            Object fieldValue = readFieldValue(entity, jsonPath);
            if (fieldValue != null) {
                data.put(targetName, fieldValue);
            }

        });
        return data;
    }

    public static Object readFieldValue(JSONObject jsonObject, String jsonPath) {
        try {
            //fix 防止当路径过程时自动换或者抒写时产生多余的空格
            String newJsonPath = jsonPath.replaceAll("\\s+", "");
            Object fieldValue = JsonPath.read(jsonObject, newJsonPath);
            return Objects.isNull(fieldValue) ? "" : fieldValue;
        } catch (Exception e) {
            log.error("JSON path {} is not valid: {}",jsonPath,e.getMessage());
            throw new PathNotFoundException("JSON path '$.name' is not valid: "+e.getMessage());
        }
    }

}
