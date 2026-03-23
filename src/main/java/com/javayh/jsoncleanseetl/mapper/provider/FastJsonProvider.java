package com.javayh.jsoncleanseetl.mapper.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.InvalidJsonException;
import com.jayway.jsonpath.spi.json.JsonProvider;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 默认值得配置
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024-03-07
 */
public class FastJsonProvider implements JsonProvider {

    @Override
    public Object parse(String json) {
        return JSON.parse(json);
    }

    @Override
    public Object parse(InputStream inputStream, String charset) throws InvalidJsonException {
        throw new UnsupportedOperationException("Parsing JSON from InputStream is not supported by FastJsonProvider");
    }

    @Override
    public String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    @Override
    public Object createArray() {
        return new JSONObject();
    }

    @Override
    public Map<String, Object> createMap() {
        return new JSONObject();
    }

    @Override
    public boolean isArray(Object obj) {
        return obj instanceof JSONObject;
    }

    @Override
    public int length(Object obj) {
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).size();
        }
        return -1;
    }

    @Override
    public Iterable<?> toIterable(Object obj) {
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).entrySet();
        }
        return null;
    }

    @Override
    public Collection<String> getPropertyKeys(Object obj) {
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).keySet();
        }
        return null;
    }

    @Override
    public Object getArrayIndex(Object obj, int idx) {
        if (obj instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) obj;
            if (idx >= 0 && idx < jsonObject.size()) {
                return jsonObject.get(Integer.toString(idx));
            }
        }
        return null;
    }

    @Override
    @Deprecated
    public Object getArrayIndex(Object obj, int idx, boolean b) {
        return getArrayIndex(obj, idx);
    }

    @Override
    public void setArrayIndex(Object obj, int idx, Object val) {
        if (obj instanceof JSONObject) {
            ((JSONObject) obj).put(Integer.toString(idx), val);
        }
    }

    @Override
    public Object getMapValue(Object obj, String key) {
        if (obj instanceof JSONObject) {
            Object value = ((JSONObject) obj).get(key);
            // 关键修复：如果值是 Map 但不是 JSONObject，需要包装成 JSONObject
            if (value instanceof Map && !(value instanceof JSONObject)) {
                return new JSONObject((Map<String, Object>) value);
            }
            if (value instanceof List && !(value instanceof JSONArray)) {
                return new JSONArray((List) value);
            }
            return value;
        }
        return null;
    }

    @Override
    public void setProperty(Object obj, Object key, Object val) {
        if (obj instanceof JSONObject && key instanceof String) {
            ((JSONObject) obj).put((String) key, val);
        }
    }

    @Override
    public void removeProperty(Object obj, Object key) {
        if (obj instanceof JSONObject && key instanceof String) {
            ((JSONObject) obj).remove((String) key);
        }
    }

    @Override
    public boolean isMap(Object obj) {
        return obj instanceof JSONObject;
    }

    @Override
    public Object unwrap(Object obj) {
        return obj;
    }
}
