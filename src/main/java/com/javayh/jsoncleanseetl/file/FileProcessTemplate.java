package com.javayh.jsoncleanseetl.file;

import java.util.List;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;
import com.javayh.jsoncleanseetl.config.DataTransformerProperties;
import com.javayh.jsoncleanseetl.config.JsonMappingProperties;

import cn.hutool.core.lang.UUID;

/**
 * <p>
 * file的处理模板
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024-02-29
 */
public interface FileProcessTemplate {


    /**
     * 保存模板
     *
     * @param file 带保存的文件
     */
    void save(MultipartFile file);

    /**
     * 解析上传的 文件
     *
     * @param file 带解析的文件
     * @return {@link JsonMappingProperties} 解析后的文件映射成mapping
     * @throws Exception 文件上传失败的异常
     */
    List<JsonMappingProperties> analysis(MultipartFile file) throws Exception;

    /**
     * 数据处理模板
     *
     * @param request 数据啊原始文件
     * @return DataTransformerProperties.TransformConfig
     * @throws FileUploadException 文件上传失败的异常
     */
    default DataTransformerProperties.TransformConfig process(FileAndJsonRequest request) throws FileUploadException {
        try {
            MultipartFile file = request.getFile();
            save(file);
            List<JsonMappingProperties> analysis = analysis(file);
            DataTransformerProperties.TransformConfig transformConfig = new DataTransformerProperties.TransformConfig();
            transformConfig.setTitle(file.getOriginalFilename());
            transformConfig.setConfigId(UUID.fastUUID().toString());
            transformConfig.setMappings(analysis);
            return transformConfig;
        } catch (Exception e) {
            throw new FileUploadException("file analysis error");
        }
    }
}
