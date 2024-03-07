package com.javayh.jsoncleanseetl.mapper;

import java.util.EnumSet;

import com.javayh.jsoncleanseetl.mapper.provider.FastJsonMappingProvider;
import com.javayh.jsoncleanseetl.mapper.provider.FastJsonProvider;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

/**
 * <p>
 * 自定义实现的json path 方式
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024-03-07
 */
public class JceJsonPathReader {

    /**
     * Option.ALWAYS_RETURN_LIST：默认情况下，当 JSON Path 表达式匹配到的结果只有一个时，会返回单个对象而不是列表。使用该 Option 后，
     * 无论匹配结果的数量是多少，都会返回一个列表。
     * <p>
     * Option.AS_PATH_LIST：使解析器返回 JSON Path 表达式所匹配到的 JSON 元素的路径列表。
     * <p>
     * Option.SUPPRESS_EXCEPTIONS：解析器不会抛出 PathNotFoundException 异常，而是返回空列表或空对象。
     * <p>
     * Option.ALWAYS_RETURN_LIST：无论匹配结果的数量是多少，解析器都会返回一个列表。
     * <p>
     * Option.DEFAULT_PATH_LEAF_TO_NULL：默认情况下，当 JSON Path 表达式匹配到的结果为叶子节点且值为 null 时，解析器会将其忽略。
     * 使用该 Option 后，解析器将返回这些叶子节点的 null 值。
     * <p>
     * Option.THROW_ON_MISSING_PROPERTY：如果 JSON Path 表达式中指定的属性不存在，则抛出 PathNotFoundException 异常。
     * <p>
     * Option.AS_VALUES：解析器将返回匹配结果的值而不是 JSON 对象。
     * 详细参考 {@link Option}
     */
    private static final Configuration CONF = Configuration.builder()
        .jsonProvider(new FastJsonProvider())
        /*
        当配置了多个 Option 时，JsonPath 解析器会按照以下规则来确定最终的行为：

        如果设置了互斥的 Option，例如 Option.ALWAYS_RETURN_LIST 和 Option.SUPPRESS_EXCEPTIONS，解析器会选择最后设置的那个 Option。

        如果设置了冲突的 Option，例如 Option.ALWAYS_RETURN_LIST 和 Option.DEFAULT_PATH_LEAF_TO_NULL，则解析器会抛出异常。

        如果设置了不冲突的 Option，例如 Option.AS_PATH_LIST 和 Option.SUPPRESS_EXCEPTIONS，则解析器会将这些 Option 结合起来使用。

        如果设置了相同的 Option 多次，例如多次设置了 Option.AS_PATH_LIST，解析器会忽略重复设置的 Option，仅保留一个。

        这样的配置只能满足大部分场景，需要根据实际需求进行调整
         */
        .options(EnumSet.of(
            Option.SUPPRESS_EXCEPTIONS,
            Option.DEFAULT_PATH_LEAF_TO_NULL
        ))
        .mappingProvider(new FastJsonMappingProvider())
        .build();

    /**
     * 自定义 JSON Path 解析方法
     *
     * @param json     原始数据
     * @param jsonPath JSON Path 路径
     * @return 解析后的数据
     */
    public static Object read(Object json, String jsonPath) {
        return JsonPath.using(CONF).parse(json).read(jsonPath);
    }

    /**
     * 自定义 JSON Path 解析方法，需要指定  {@link Configuration}
     *
     * @param json     原始数据
     * @param jsonPath JSON Path 路径
     * @param conf     {@link Configuration} 自定义配置实现
     * @return 解析后的数据
     */
    public static Object read(Object json, String jsonPath, Configuration conf) {
        return JsonPath.using(conf).parse(json).read(jsonPath);
    }

}
