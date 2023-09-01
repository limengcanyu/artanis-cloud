package com.spring.cloud.common.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class ReceiveMessageService {

    @KafkaListener(topics = KafkaConstant.STRING_TOPIC, groupId = KafkaConstant.CONSUMER_GROUP_ID)
    public void processMessage(String content) {
        log.debug("topic: {} receive message: {}", KafkaConstant.STRING_TOPIC, content);
    }

    @KafkaListener(topics = KafkaConstant.OBJECT_TOPIC_1, groupId = KafkaConstant.CONSUMER_GROUP_ID)
    public void processMessage(Map<String, Object> content) {
        log.debug("topic: {} receive message: {}", KafkaConstant.OBJECT_TOPIC_1, content);
    }

    @KafkaListener(id = "thing1", groupId = KafkaConstant.CONSUMER_GROUP_ID,
            topicPartitions = {@TopicPartition(topic = KafkaConstant.OBJECT_TOPIC_2, partitions = {"0", "1", "2"})})
    public void processMessage2(Map<String, Object> content) {
        log.debug("topic: {} receive message: {}", KafkaConstant.OBJECT_TOPIC_2, content);
    }

}
