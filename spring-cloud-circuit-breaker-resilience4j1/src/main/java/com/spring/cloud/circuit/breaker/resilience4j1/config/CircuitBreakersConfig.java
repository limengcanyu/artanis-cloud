package com.spring.cloud.circuit.breaker.resilience4j1.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/5/23 19:59
 */
@Configuration
public class CircuitBreakersConfig {
    /**
     * Configuring Resilience4J Circuit Breakers
     * <p>
     * Default Configuration
     *
     * @return
     */
    @Primary
    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build()) // 超时断路
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .build());
    }

//    /**
//     * Configuring Resilience4J Circuit Breakers
//     *
//     * Specific Circuit Breaker Configuration
//     *
//     * @return
//     */
//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> delayCustomizer() {
//        return factory -> factory.configure(builder -> builder.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
//                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build()), "delay");
//    }

//    /**
//     * Configuring Resilience4J Circuit Breakers
//     *
//     * In addition to configuring the circuit breaker that is created you can also customize the circuit breaker after it has been created but before it is returned to the caller.
//     * To do this you can use the addCircuitBreakerCustomizer method. This can be useful for adding event handlers to Resilience4J circuit breakers.
//     *
//     * @return
//     */
//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> slowCustomizer() {
//        return factory -> factory.addCircuitBreakerCustomizer(circuitBreaker -> circuitBreaker.getEventPublisher()
//                .onError(normalFluxErrorConsumer).onSuccess(normalFluxSuccessConsumer), "normalflux");
//    }

//    /**
//     * Configuring Spring Retry Circuit Breakers
//     *
//     * Default Configuration
//     *
//     * @return
//     */
//    @Bean
//    public Customizer<SpringRetryCircuitBreakerFactory> defaultRetryCustomizer() {
//        return factory -> factory.configureDefault(id -> new SpringRetryConfigBuilder(id)
//                .retryPolicy(new TimeoutRetryPolicy()).build());
//    }

//    /**
//     * Configuring Spring Retry Circuit Breakers
//     *
//     * Specific Circuit Breaker Configuration
//     *
//     * @return
//     */
//    @Bean
//    public Customizer<SpringRetryCircuitBreakerFactory> delayRetryCustomizer() {
//        return factory -> factory.configure(builder -> builder.retryPolicy(new SimpleRetryPolicy(3)).build(), "delay");
//    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }

    /**
     * 需要定义RetryTemplate，否则无法启动
     * <p>
     * Consider defining a bean of type 'org.springframework.retry.support.RetryTemplate' in your configuration.
     *
     * @return
     */
    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(2));
        return retryTemplate;
    }
}
