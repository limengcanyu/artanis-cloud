package com.spring.cloud.stream.kafka.producer.config;

import com.spring.cloud.stream.kafka.producer.KafkaProducerApplication;
import org.springframework.cloud.stream.binder.PartitionKeyExtractorStrategy;
import org.springframework.messaging.Message;

/**
 * <p>Description: 从Message中提取partition key的策略</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2019/3/19 0019
 */
public class CustomPartitionKeyExtractorClass implements PartitionKeyExtractorStrategy {
    @Override
    public Object extractKey(Message<?> message) {

        Object obj = message.getPayload();
        System.out.println("消息载荷：" + obj);

        if (obj instanceof KafkaProducerApplication.Person) {
            KafkaProducerApplication.Person person = (KafkaProducerApplication.Person) obj;
            return person.getId();
        }

        return null;
    }

}
