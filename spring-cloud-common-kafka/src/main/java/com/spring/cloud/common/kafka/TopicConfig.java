package com.spring.cloud.common.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    @Bean
    public NewTopic stringTopic() {
        return new NewTopic(KafkaConstant.STRING_TOPIC, 3, (short) 3);
    }

    @Bean
    public NewTopic objectTopic1() {
        return TopicBuilder.name(KafkaConstant.OBJECT_TOPIC_1)
                .partitions(1)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic objectTopic2() {
        return TopicBuilder.name(KafkaConstant.OBJECT_TOPIC_2)
                .partitions(3)
                .replicas(3)
                .build();
    }

}
