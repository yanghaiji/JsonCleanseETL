package com.javayh.jsonhttpextractor.etl;

import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * json etl
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024-02-22
 */
public interface DataTransformer {

    /**
     * <p>
     * 数据提取，主要用于http/https/或者上传的数据的提取
     * </p>
     *
     * @param data 元数据
     * @return T 返回值的泛型
     * @version 1.0.0
     * @author hai ji
     * @since 2024/2/22
     */
    <T> T extract(String data);

    /**
     * <p>
     * 数据转换，将上一步提取的数据，进行转换
     * </p>
     *
     * @param data   元数据
     * @param tClass 目标数据结构体，
     * @return T
     * @version 1.0.0
     * @author hai ji
     * @since 2024/2/22
     */
    <T> T transform(JSONObject data, Class<T> tClass);

}
