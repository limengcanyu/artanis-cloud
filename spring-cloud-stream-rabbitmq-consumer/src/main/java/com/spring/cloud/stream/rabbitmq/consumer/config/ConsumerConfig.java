package com.spring.cloud.stream.rabbitmq.consumer.config;

import com.spring.cloud.stream.rabbitmq.consumer.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class ConsumerConfig {

    @Bean
    public Consumer<Person> log() {
        return person -> {
            System.out.println("Received: " + person);
        };
    }
}
