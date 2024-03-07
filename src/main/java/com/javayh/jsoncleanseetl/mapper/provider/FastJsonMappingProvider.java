package com.javayh.jsoncleanseetl.mapper.provider;

import com.alibaba.fastjson.JSON;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.mapper.MappingProvider;

/**
 * <p>
 * 默认值得配置 {@link MappingProvider}
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024-03-07
 */
public class FastJsonMappingProvider implements MappingProvider {

    @Override
    public <T> T map(Object source, Class<T> targetType, Configuration configuration) {
        // 将 JSON 数据映射到 Java 对象
        return JSON.parseObject(JSON.toJSONString(source), targetType);
    }

    @Override
    public <T> T map(Object source, TypeRef<T> targetType, Configuration configuration) {
        // 将 JSON 数据映射到 Java 对象
        return JSON.parseObject(JSON.toJSONString(source), targetType.getType());
    }

}
