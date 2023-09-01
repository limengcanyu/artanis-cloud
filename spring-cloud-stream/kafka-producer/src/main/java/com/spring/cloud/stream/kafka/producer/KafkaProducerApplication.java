package com.spring.cloud.stream.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableBinding(Source.class)
@EnableScheduling
@SpringBootApplication
public class KafkaProducerApplication {

    @Autowired
    private Source source;

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

    @Scheduled(fixedRate = 5000)
    public void handle1() {
        Person person = new Person();

        Long currentTimeMillis = System.currentTimeMillis();

        person.setId(Long.parseLong(currentTimeMillis.toString().substring(currentTimeMillis.toString().length() - 1)));
        person.setName("rock ");

        System.out.println("send a person..." + person);

        source.output().send(MessageBuilder.withPayload(person).build());
    }

    public static class Person {
        private Long id;
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
