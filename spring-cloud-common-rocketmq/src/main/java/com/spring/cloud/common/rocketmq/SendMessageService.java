package com.spring.cloud.common.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendMessageService {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(String topic, Object content) {
        SendResult sendResult = rocketMQTemplate.syncSend(topic, content);
        log.debug("syncSend1 to topic {} sendResult={}", RocketMQConstant.USER_TOPIC, sendResult);
    }
}
