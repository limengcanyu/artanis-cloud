package com.spring.cloud.nacos.microservice1;

import com.spring.cloud.nacos.microservice1.config.MyRibbonLoadBalancerClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class SpringCloudNacosMicroservice1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNacosMicroservice1Application.class, args);
    }

    @Bean
    MyRibbonLoadBalancerClient loadBalancerClient(SpringClientFactory clientFactory) {
        return new MyRibbonLoadBalancerClient(clientFactory);
    }
}
