package com.spring.cloud.common.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReceiveMessageService {

    @RabbitListener(queues = "someQueue")
    public void processMessage(String content) {
        log.debug("queue: someQueue receive message: {}", content);
    }

}
