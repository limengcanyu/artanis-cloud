package com.spring.cloud.common.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * RocketMQMessageListener
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = RocketMQConstant.USER_TOPIC, consumerGroup = "user_consumer")
//@RocketMQMessageListener(nameServer = "${demo.rocketmq.myNameServer}", topic = "${demo.rocketmq.topic.user}", consumerGroup = "user_consumer")
public class UserConsumer implements RocketMQListener<User> {

    @Override
    public void onMessage(User message) {
        log.debug("######## user_consumer received: {} ; age: {} ; name: {}", message, message.getUserAge(), message.getUserName());
    }

}
