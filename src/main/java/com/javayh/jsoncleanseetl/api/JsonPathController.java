package com.javayh.jsoncleanseetl.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.javayh.jsoncleanseetl.mapper.ReflectiveMapper;

/**
 * <p>
 * 用于json path书写得测试
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024-02-28
 */
@Controller
public class JsonPathController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    /**
     * 用于测试json path的准确性
     *
     * @param data 目标json data 与 json path
     * @return 解析后的处理逻辑
     */
    @PostMapping(value = "/jsonpath")
    @ResponseBody
    public ResponseEntity jsonPathTest(@RequestBody JSONObject data) {
        return ResponseEntity.ok(ReflectiveMapper.readFieldValue(data.getJSONObject("data"), data.getString("jsonPath")));
    }
}
