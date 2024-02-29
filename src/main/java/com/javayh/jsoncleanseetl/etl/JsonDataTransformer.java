package com.javayh.jsoncleanseetl.etl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.javayh.jsoncleanseetl.config.DataTransformerProperties;
import com.javayh.jsoncleanseetl.exception.JsonConfigException;
import com.javayh.jsoncleanseetl.http.HttpUtils;
import com.javayh.jsoncleanseetl.http.ImportJsonRequest;
import com.javayh.jsoncleanseetl.http.SyncJsonRequest;
import com.javayh.jsoncleanseetl.mapper.ReflectiveMapper;

/**
 * <p>
 * json
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024-02-22
 */
@Component
public class JsonDataTransformer implements DataTransformer {

    @Autowired
    private DataTransformerProperties transformerProperties;


    /**
     * <p>
     * 数据提取
     * </p>
     *
     * @param data 元数据
     * @return T 返回值的泛型
     * @version 1.0.0
     * @author hai ji
     * @since 2024/2/22
     */
    @Override
    public <T> T extract(SyncJsonRequest data) {
        return (T) HttpUtils.sendRequest(data);
    }

    /**
     * <p>
     * 数据转换
     * </p>
     *
     * @param data 元数据
     * @return T
     * @version 1.0.0
     * @author hai ji
     * @since 2024/2/22
     */
    @Override
    public <T> T transform(ImportJsonRequest data) {
        String confId = data.getConfId();
        Optional<DataTransformerProperties.TransformConfig> first = transformerProperties.getTransforms().stream()
            .filter(transformConfig -> transformConfig.getConfigId().equals(confId)).findFirst();
        if (first.isPresent()) {
            DataTransformerProperties.TransformConfig transformConfig = first.get();
            return (T) ReflectiveMapper.mapper(data.getData(), transformConfig.getMappings());
        }
        throw new JsonConfigException(confId + "mapping configuration missing; please check your " +
            "dataTransformerProperties configuration.");
    }
}
