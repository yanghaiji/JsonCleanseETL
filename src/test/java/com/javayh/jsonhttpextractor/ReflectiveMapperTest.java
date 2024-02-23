package com.javayh.jsonhttpextractor;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.javayh.jsonhttpextractor.mapper.ReflectiveMapper;

@SpringBootTest
public class ReflectiveMapperTest {

    @Autowired
    private ReflectiveMapper reflectiveMapper;

    @Test
    public void testMappingToMap() throws Exception {
        String jsonString = "{\"name\":\"John\",\"age\":30,\"address\":{\"city\":\"New York\",\"zip\":\"10001\"}}";
        Map<String, Object> map = reflectiveMapper.map(JSONObject.parseObject(jsonString), HashMap.class);
        System.out.println("Mapped to Map object: " + JSON.toJSONString(map));
    }
}
