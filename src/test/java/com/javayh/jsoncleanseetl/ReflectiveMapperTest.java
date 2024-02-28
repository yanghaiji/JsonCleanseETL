package com.javayh.jsoncleanseetl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.javayh.jsoncleanseetl.mapper.ReflectiveMapper;
import com.javayh.jsoncleanseetl.util.JsonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ReflectiveMapperTest {

    @Autowired
    private ReflectiveMapper reflectiveMapper;

    /**
     * 简单的数据节点的移动
     *
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
        JSONObject map = reflectiveMapper.transformer(JSONObject.parseObject(jsonString), "type1");
        System.out.println("Mapped to Map object: " + JSON.toJSONString(map));
    }

    /**
     * 测试人员信息的提取
     * 提取后的json
     * <p>
     * {
     * "id": "123456789",
     * "userName": "张三",
     * "email": "zhangsan@example.com",
     * "birthdate": "1990-01-01",
     * "address": {
     * "city": "北京",
     * "street": "中山路",
     * "postal_code": "100000"
     * },
     * "balance": 1500.50,
     * "securityQuestions": [
     * {
     * "question": "你的首选宠物是什么？",
     * "answer": "猫"
     * },
     * {
     * "question": "你的第一个老师叫什么？",
     * "answer": "李老师"
     * }
     * ]
     * }
     *
     * @throws Exception
     */
    @Test
    public void testUserInfo() throws Exception {
        JSONObject json = JsonUtils.readJsonFromClassPath("json/userInfo.json", JSONObject.class);
        JSONObject map = reflectiveMapper.transformer(json, "userInfo.json");
        log.info("Mapped to Map object: {}", JSON.toJSONString(map));
    }
}
