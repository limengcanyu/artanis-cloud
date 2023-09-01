package com.spring.cloud.common.rabbitmq;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class SendMessageService {
    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    public void sendMessage(String exchange, String routingKey, Message<?> message) {
        rabbitMessagingTemplate.send(exchange, routingKey, message);
    }

}
