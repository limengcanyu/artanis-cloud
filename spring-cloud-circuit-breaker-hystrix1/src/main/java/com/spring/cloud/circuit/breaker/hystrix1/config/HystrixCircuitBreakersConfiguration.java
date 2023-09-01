package com.spring.cloud.circuit.breaker.hystrix1.config;

import com.netflix.hystrix.HystrixCommandProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2020/04/19 19:33
 */
@Slf4j
@Configuration
public class HystrixCircuitBreakersConfiguration {
//    /**
//     * Default Configuration
//     *
//     * @return
//     */
//    @Bean
//    public Customizer<HystrixCircuitBreakerFactory> defaultConfig() {
//        return factory -> factory.configureDefault(id -> HystrixCommand.Setter
//                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(id))
//                .andCommandPropertiesDefaults(
//                        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(3000)
//                )
//        );
//    }

    /**
     * Specific Circuit Breaker Configuration
     *
     * @return
     */
    @Bean
    public Customizer<HystrixCircuitBreakerFactory> customizer() {
        log.debug("====== Specific Circuit Breaker Configuration");
        return factory -> factory.configure(builder -> builder.commandProperties(
                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(2000)
                ),
                "foo", "bar"
        );
    }
}
