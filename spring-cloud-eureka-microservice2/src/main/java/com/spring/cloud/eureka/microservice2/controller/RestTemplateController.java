package com.spring.cloud.eureka.microservice2.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
}
