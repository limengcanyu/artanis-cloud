package com.spring.cloud.stream.kafka.producer;


import com.spring.cloud.stream.kafka.producer.channel.OutputChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding({OutputChannel.class})
@SpringBootApplication
public class SpringCloudStreamKafkaProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamKafkaProducerApplication.class, args);
    }
}
