package com.javayh.jsoncleanseetl.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * 统一异常处理
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024/2/27
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常
     *
     * @param ex 异常信息
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSONObject> handleException(Exception ex) {
        // 构建统一的JSON格式错误信息
        String errorMessage = "An error occurred: " + ex.getMessage();
        JSONObject data = new JSONObject();
        data.put("message", errorMessage);
        return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 参数校验异常
     *
     * @param ex 异常信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errors = bindingResult.getFieldErrors().stream()
            .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
            .collect(Collectors.toList());
        JSONObject data = new JSONObject();
        data.put("message", errors);
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
