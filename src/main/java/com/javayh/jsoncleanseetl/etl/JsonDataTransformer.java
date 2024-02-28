package com.javayh.jsoncleanseetl.etl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.javayh.jsoncleanseetl.config.DataTransformerProperties;
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
        return (T) reflectiveMapper.transformer(data.getData(), data.getConfId());
    }
}
