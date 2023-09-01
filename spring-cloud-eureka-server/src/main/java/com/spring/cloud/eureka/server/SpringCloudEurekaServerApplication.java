package com.spring.cloud.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Spring Cloud Eureka Server Application
 *
 * @author rock.jxf
 * @date 2019/5/26
 */
@EnableEurekaServer
@SpringBootApplication
public class SpringCloudEurekaServerApplication {
    public static void main(String[] args) {
//        System.setProperty("spring.profiles.active", "standalone");

        SpringApplication.run(SpringCloudEurekaServerApplication.class, args);
    }
}
