package com.spring.cloud.common.rocketmq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SendMessageServiceTests {
    @Autowired
    private SendMessageService sendMessageService;

    @Test
    public void sendMessage() {
        sendMessageService.sendMessage(RocketMQConstant.USER_TOPIC, User.builder().userAge((byte) 18).userName("Kitty").build());
    }
}
