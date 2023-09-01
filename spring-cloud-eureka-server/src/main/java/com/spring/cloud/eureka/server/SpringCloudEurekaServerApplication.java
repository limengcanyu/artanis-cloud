package com.spring.cloud.eureka.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

@RestController
@Slf4j
@EnableEurekaServer
@SpringBootApplication
public class SpringCloudEurekaServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringCloudEurekaServerApplication.class, args);
    }

    /**
     * http://localhost:8761/hello
     *
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        List<Map<String, String>> mapList = new ArrayList<>();
        for (int i = 0; i < 1000000000; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("key", "000000000000000000000000000000000000000000000000000000000000000");
            mapList.add(map);
        }

        return "hello";
    }

}
