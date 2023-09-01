package com.spring.cloud.stream.rabbitmq.producer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 生产者
 */
@Slf4j
@Configuration
public class SupplierConfig {

//    @Bean
//    public Supplier<String> stringSupplier() {
//        String msg = "string Supplier";
//        log.debug("============ stringSupplier output: {}", msg);
//        return () -> msg;
//    }

    @Bean
    public Supplier<Flux<String>> stringSupplier() {
        return () -> Flux.fromStream(Stream.generate(() -> {
            try {
                Thread.sleep(1000);
                return "Hello from Supplier";
            } catch (Exception e) {
                // ignore
                return "exception return";
            }
        })).subscribeOn(Schedulers.elastic()).share();
    }
}
