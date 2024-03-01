package com.javayh.jsoncleanseetl.file;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.javayh.jsoncleanseetl.config.JsonMappingProperties;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * file的处理模板
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024-02-29
 */
@Slf4j
@Service
public class FileProcessTemplateImpl implements FileProcessTemplate {

    /**
     * 保存模板
     *
     * @param file 带保存的文件
     */
    @Override
    public void save(MultipartFile file) {

    }

    /**
     * 解析上传的 文件
     *
     * @param file 带解析的文件
     * @return {@link JsonMappingProperties} 解析后的文件映射成mapping
     */
    @Override
    public List<JsonMappingProperties> analysis(MultipartFile file) throws Exception {
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        return ExcelImportUtil.importExcel(file.getInputStream(), JsonMappingProperties.class, params);

    }

}
