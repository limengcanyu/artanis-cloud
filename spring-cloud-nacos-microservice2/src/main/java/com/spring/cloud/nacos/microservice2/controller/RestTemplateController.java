package com.spring.cloud.nacos.microservice2.controller;

import com.spring.cloud.nacos.microservice2.feign.Service3Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/8/16 10:36
 */
@RestController
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Service3Client service3Client;

    /**
     * localhost:8810/callService1
     *
     * Calling REST Services with RestTemplate
     *
     * @return
     * @throws URISyntaxException
     */
    @RequestMapping("/callService1")
    public String callService1() throws URISyntaxException {
        URI url = new URI("http://localhost:8800/echo/123");
        String str = restTemplate.getForObject(url, String.class);
        return str;
    }

    /**
     * localhost:8810/echoService2/content
     *
     * @param content
     * @return
     */
    @RequestMapping("/echoService2/{content}")
    public String echoService2(@PathVariable String content) {
        return "service2 return " + service3Client.echoService3(content);
    }
}
