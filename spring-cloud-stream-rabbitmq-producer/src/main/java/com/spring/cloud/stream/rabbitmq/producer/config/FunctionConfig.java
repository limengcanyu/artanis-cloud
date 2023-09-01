package com.spring.cloud.stream.rabbitmq.producer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * Function定义的既是生产者，也是消费者
 *
 *
 */
@Slf4j
@Configuration
public class FunctionConfig {

    @Bean
    public Function<String, String> inputCase() {
        return value -> {
            String retString = value.toLowerCase() + " inputCase";
            log.debug("============ inputCase input: {} output: {}", value, retString);
            return retString;
        };
    }

    @Bean
    public Function<String, String> lowerCase() {
        return value -> {
            String retString = value.toLowerCase() + " lowerCase";
            log.debug("============ lowerCase input: {} output: {}", value, retString);
            return retString;
        };
    }

    @Bean
    public Function<String, String> uppercase() {
        return value -> {
            String retString = value.toUpperCase() + " uppercase";
            log.debug("============ uppercase input: {} output: {}", value, retString);
            return retString;
        };
    }
}
