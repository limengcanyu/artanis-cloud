package com.spring.cloud.common.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.PartitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@Slf4j
@Component
public class SendMessageService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topic, Object data) {
//        ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(topic, data);
//        send.addCallback(callback(topic));
    }

    public void sendMessage(String topic, String key, Object data) {
//        ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(topic, key, data);
//        send.addCallback(callback(topic));
    }

    public void sendMessage(String topic, Integer partition, String key, Object data) {
//        ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(topic, partition, key, data);
//        send.addCallback(callback(topic));
    }

    public ListenableFutureCallback<SendResult<String, Object>> callback(String topic) {
        return new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                log.debug("topic: {} send data failed", topic, ex);
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.debug("topic: {} send data success SendResult: {}", topic, result);
            }
        };
    }

    public List<PartitionInfo> partitionsFor(String topic) {
        return kafkaTemplate.partitionsFor(topic);
    }

}
