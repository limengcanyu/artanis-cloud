package com.spring.cloud.stream.kafka.consumer;


import com.spring.cloud.stream.kafka.consumer.channel.InputChannel;
import com.spring.cloud.stream.kafka.consumer.message.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding({InputChannel.class})
@SpringBootApplication
public class SpringCloudStreamKafkaConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamKafkaConsumerApplication.class, args);
    }

    /**
     * 监听输入Channel: input
     * 也即监听Topic：input
     *
     * @param user
     */
    @StreamListener(InputChannel.INPUT)
    public void processInput(User user) {
        System.out.println("topic: " + InputChannel.INPUT  + " received message: " + user);
    }

}
