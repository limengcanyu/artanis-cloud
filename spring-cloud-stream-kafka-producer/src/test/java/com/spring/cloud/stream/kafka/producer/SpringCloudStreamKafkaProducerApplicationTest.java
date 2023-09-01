package com.spring.cloud.stream.kafka.producer;

import com.spring.cloud.stream.kafka.producer.channel.OutputChannel;
import com.spring.cloud.stream.kafka.producer.message.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext
public class SpringCloudStreamKafkaProducerApplicationTest {
    @Autowired
    private OutputChannel outputChannel;

    @Test
    public void outputChannel() {
        try {
            for (int i = 1; i <= 10; i++) {
                Message<User> message = new GenericMessage<>(new User("userId" + i, "userName" + i));
                // 向输入Channel（input）发送消息，也即想Topic（input）发送消息
                outputChannel.output().send(message);

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
