package com.spring.cloud.sleuth.skywalking3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
public class SpringCloudSleuthSkywalking3Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSleuthSkywalking3Application.class, args);
    }

    /**
     * localhost:8820/echoSkywalking3/Skywalking3
     *
     * @param content
     * @return
     */
    @RequestMapping("/echoSkywalking3/{content}")
    public String echoSkywalking3(@PathVariable String content) {
        log.debug("echoSkywalking3 receive param: {}", content);
        return "Skywalking3 return " + content;
    }

}
