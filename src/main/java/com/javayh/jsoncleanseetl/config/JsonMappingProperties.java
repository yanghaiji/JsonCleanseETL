package com.javayh.jsoncleanseetl.config;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.entity.ImportParams;
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
     * 源字段的名字
     */
    @Excel(name = "SourceName")
    private String sourceName;

    /**
     * 目标字段的名字，用于生成后字段名字的修改
     */
    @Excel(name = "TargetName")
    private String targetName;

    /**
     * 取数的json path
     */
    @Excel(name = "Path")
    private String path;

    /**
     * 取值的一些描述，方便后期的维护
     */
    @Excel(name = "Describe")
    private String describe;

    /**
     * 附加操作，预留字段
     */
    @Excel(name = "Operation")
    private String operation;


}
