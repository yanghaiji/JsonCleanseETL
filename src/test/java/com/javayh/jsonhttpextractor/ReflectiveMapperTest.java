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

    /**
     * 简单的数据节点的移动
     * @throws Exception
     */
    @Test
    public void testMappingToMap() throws Exception {
        String jsonString = "{\n" +
            "    \"name\": \"John\",\n" +
            "    \"age\": 30,\n" +
            "    \"wwww\": {\n" +
            "        \"address\": {\n" +
            "            \"city\": \"New York\",\n" +
            "            \"zip\": \"10001\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
        JSONObject map = reflectiveMapper.transformer(JSONObject.parseObject(jsonString),"type1");
        System.out.println("Mapped to Map object: " + JSON.toJSONString(map));
    }
}
