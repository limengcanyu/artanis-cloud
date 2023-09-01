package com.spring.cloud.circuit.breaker.hystrix2.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
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
//                        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000)
//                )
//        );
//    }

//    /**
//     * Specific Circuit Breaker Configuration
//     *
//     * @return
//     */
//    @Bean
//    public Customizer<HystrixCircuitBreakerFactory> customizer() {
//        return factory -> factory.configure(builder -> builder.commandProperties(
//                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(2000)
//                ),
//                "foo", "bar"
//        );
//    }
}
