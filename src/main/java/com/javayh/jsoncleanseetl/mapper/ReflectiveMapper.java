package com.javayh.jsoncleanseetl.mapper;

import java.util.List;
import java.util.Optional;

import org.springframework.util.CollectionUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javayh.jsoncleanseetl.config.JsonMappingProperties;
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
            String sourceName = jsonMapping.getSourceName();
            boolean nullable = jsonMapping.isNullable();
            Optional<Object> fieldValue = readFieldValue(entity, jsonPath);
            fieldValue.ifPresent(value -> {
                try {
                    if (isNonEmptyValue(value) && !nullable) {
                        data.put(targetName, value);
                    } else {
                        log.warn("Optional get field value is empty for source field '{}' using path '{}'", sourceName,
                            jsonPath);
                        // fieldValue 为空，根据规则要求处理，例如将目标字段置为 null 或其他默认值
                        // todo 或者根据具体业务需求设定其他默认值
                        data.put(targetName, null);
                    }
                } catch (Exception e) {
                    // 在发生异常时，可以根据具体业务需求处理，例如记录日志、继续执行下一个字段的转换等
                    log.error("Error parsing value for source field '{}' using path '{}': {}",
                        sourceName, jsonPath, e.getMessage());
                }
            });
            if (!fieldValue.isPresent()) {
                log.error("Field value is empty for source field '{}' using path '{}'", sourceName, jsonPath);
                // todo 或者根据具体业务需求设定其他默认值
                data.put(targetName, null);
            }
        });
        return data;
    }

    public static Optional<Object> readFieldValue(JSONObject jsonObject, String jsonPath) {
        try {
            // fix 防止当路径过程时自动换或者抒写时产生多余的空格
            String newJsonPath = jsonPath.replaceAll("\\s+", "");
            return Optional.ofNullable(JceJsonPathReader.read(jsonObject, newJsonPath));
        } catch (Exception e) {
            log.error("JSON path {} is not valid: {}", jsonPath, e.getMessage());
            throw new PathNotFoundException("JSON path [" + jsonPath + "] is not valid: " + e.getMessage());
        }
    }

    public static boolean isNonEmptyValue(Object value) {
        return !(value instanceof JSONObject && ((JSONObject) value).isEmpty()) &&
            !(value instanceof JSONArray && ((JSONArray) value).isEmpty()) &&
            !(value instanceof String && ((String) value).isEmpty());
    }
}
