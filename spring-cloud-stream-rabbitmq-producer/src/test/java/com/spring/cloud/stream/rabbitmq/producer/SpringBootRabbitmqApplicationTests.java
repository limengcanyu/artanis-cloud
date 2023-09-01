package com.spring.cloud.stream.rabbitmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.cloud.stream.test.matcher.MessageQueueMatcher.receivesPayloadThat;

@Slf4j
@Import({TestChannelBinderConfiguration.class})
@SpringBootTest
public class SpringBootRabbitmqApplicationTests {
//    @Autowired
//    private InputDestination input;
//
//    @Autowired
//    private OutputDestination output;

    @Autowired
    @Qualifier("uppercase-in-0")
    private MessageChannel input;

    @Autowired
    @Qualifier("uppercase-out-0")
    private MessageChannel output;

    @Test
    void contextLoads() {
//        input.send(new GenericMessage<>("hello".getBytes()));
////        assertThat(output.receive().getPayload()).isEqualTo("HELLO".getBytes());
//        System.out.println(Arrays.toString(output.receive().getPayload()));
//        System.out.println(Arrays.toString("HELLO".getBytes()));


        this.input.send(new GenericMessage<>("odd"));
        this.input.send(new GenericMessage<>("even"));
        this.input.send(new GenericMessage<>("odd meets even"));
        this.input.send(new GenericMessage<>("nothing but the best test"));
    }

}
