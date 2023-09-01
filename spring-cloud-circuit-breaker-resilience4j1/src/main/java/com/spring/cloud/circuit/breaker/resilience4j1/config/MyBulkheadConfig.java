package com.spring.cloud.circuit.breaker.resilience4j1.config;

import io.github.resilience4j.bulkhead.*;
import io.github.resilience4j.bulkhead.internal.SemaphoreBulkhead;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/9/13 13:55
 */
@Configuration
public class MyBulkheadConfig {
    @Bean
    ThreadPoolBulkhead threadPoolBulkhead() {
        ThreadPoolBulkheadConfig config = ThreadPoolBulkheadConfig.custom()
                .maxThreadPoolSize(10)
                .coreThreadPoolSize(2)
                .queueCapacity(20)
                .build();

        // Create a BulkheadRegistry with a custom global configuration
        ThreadPoolBulkheadRegistry registry = ThreadPoolBulkheadRegistry.of(config);

        // Get or create a ThreadPoolBulkhead from the registry -
        // bulkhead will be backed by the default config
        ThreadPoolBulkhead threadPoolBulkhead = registry.bulkhead("threadPoolBulkhead");

        return threadPoolBulkhead;
    }

    @Bean
    SemaphoreBulkhead semaphoreBulkhead() {
        // Create a custom configuration for a Bulkhead
        BulkheadConfig config = BulkheadConfig.custom()
                .maxConcurrentCalls(1)
                .maxWaitDuration(Duration.ofMillis(500))
                .build();

        // Create a BulkheadRegistry with a custom global configuration
        BulkheadRegistry registry = BulkheadRegistry.of(config);

        SemaphoreBulkhead semaphoreBulkhead = new SemaphoreBulkhead("semaphoreBulkhead", config);

        return semaphoreBulkhead;
    }
}
