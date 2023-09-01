package com.spring.cloud.eureka.microservice1;

import com.spring.cloud.eureka.microservice1.feignclient.Service2Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//@EnablePrometheusEndpoint // Prometheus add
//@EnableSpringBootMetricsCollector // Prometheus add
@RestController
@EnableFeignClients
@SpringBootApplication
public class SpringCloudEurekaMicroservice1Application {

    @Autowired
    private Service2Client service2Client;

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaMicroservice1Application.class, args);
    }

    /**
     * localhost:8800/echoService1/service1
     *
     * @param param
     * @return
     */
    @RequestMapping("/echoService1/{param}")
    public String echoService1(@PathVariable String param) {
        log.debug("call echoService1 ====================================");
        return "echo service1 param: " + service2Client.echoService2(param);
    }

}
