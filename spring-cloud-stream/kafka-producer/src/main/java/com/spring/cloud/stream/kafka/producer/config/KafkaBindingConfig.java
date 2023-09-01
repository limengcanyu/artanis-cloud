package com.spring.cloud.stream.kafka.producer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2019/3/19 0019
 */
@Configuration
public class KafkaBindingConfig {
    @Bean
    public CustomPartitionKeyExtractorClass customPartitionKeyExtractor() {
        return new CustomPartitionKeyExtractorClass();
    }

    @Bean
    public CustomPartitionSelectorClass customPartitionSelector() {
        return new CustomPartitionSelectorClass();
    }

}
