package com.javayh.jsoncleanseetl.config;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024-02-26
 */

@Data
public class JsonMappingProperties {

    /**
     * 附加操作，预留字段
     */
    private String operation;

    /**
     * 取值的一些描述，方便后期的维护
     */
    private String describe;

    /**
     * 源字段的名字
     */
    private String sourceName;

    /**
     * 目标字段的名字，用于生成后字段名字的修改
     */
    private String targetName;

    /**
     * 取数的json path
     */
    private String path;

}
