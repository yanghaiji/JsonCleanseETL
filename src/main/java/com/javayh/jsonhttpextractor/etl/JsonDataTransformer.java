package com.javayh.jsonhttpextractor.etl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.javayh.jsonhttpextractor.config.DataTransformerProperties;
import com.javayh.jsonhttpextractor.mapper.ReflectiveMapper;

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

    @Autowired
    private ReflectiveMapper reflectiveMapper;


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
    public <T> T extract(String data) {
        return null;
    }

    /**
     * <p>
     * 数据转换
     * </p>
     *
     * @param data   元数据
     * @param tClass 目标数据结构体，
     * @return T
     * @version 1.0.0
     * @author hai ji
     * @since 2024/2/22
     */
    @Override
    public <T> T transform(JSONObject data) {
//        return (T) reflectiveMapper.map(data);
        return null;
    }
}
