package com.spring.cloud.sleuth.zipkin2;

import com.spring.cloud.sleuth.zipkin2.feign.Zipkin3Client;
import com.spring.cloud.sleuth.zipkin2.feign.Zipkin4Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableFeignClients
@SpringBootApplication
public class SpringCloudSleuthZipkin2Application {
    private static Logger logger = LoggerFactory.getLogger(SpringCloudSleuthZipkin2Application.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSleuthZipkin2Application.class, args);
    }

    @Autowired
    private Zipkin3Client zipkin3Client;

    @Autowired
    private Zipkin4Client zipkin4Client;

    /**
     * localhost:8870/echoZipkin2/zipkin2
     *
     * @param content
     * @return
     */
    @RequestMapping("/echoZipkin2/{content}")
    public String echoZipkin2(@PathVariable String content){
        long current = System.currentTimeMillis();

        if (current % 2 == 0) {
            return "zipkin2 return " + zipkin3Client.echoZipkin3(content);
        } else {
            return "zipkin2 return " + zipkin4Client.echoZipkin4(content);
        }

    }

}
