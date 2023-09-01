package com.spring.cloud.stream.rabbitmq.producer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class ConsumerConfig {
    @Bean
    public Consumer<String> stringConsumer() {
        return value -> {
            log.debug("============ stringConsumer input: {}", value);
        };
    }
}
