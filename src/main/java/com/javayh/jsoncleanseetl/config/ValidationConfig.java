package com.javayh.jsoncleanseetl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author HaiJiYang
 */
@Configuration
public class ValidationConfig {

    @Bean
    public Validator validator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }
}
