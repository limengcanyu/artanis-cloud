package com.spring.cloud.sleuth.zipkin1;

import com.spring.cloud.sleuth.zipkin1.feign.Zipkin2Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableFeignClients
@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudSleuthZipkin1Application {
    private static Logger logger = LoggerFactory.getLogger(SpringCloudSleuthZipkin1Application.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSleuthZipkin1Application.class, args);
    }

    @Autowired
    private Zipkin2Client zipkin2Client;

    /**
     * localhost:8860/echoZipkin1/zipkin1
     *
     * @param content
     * @return
     */
    @RequestMapping("/echoZipkin1/{content}")
    public String echoZipkin1(@PathVariable String content){
        return "zipkin1 return " + zipkin2Client.echoZipkin2(content);
    }

}
