package com.spring.cloud.nacos.microservice1.controller;

import com.spring.cloud.nacos.microservice1.feign.Service2Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2019/12/25 10:57
 */
@Slf4j
@RestController
public class EchoController {

    @Autowired
    private Service2Client service2Client;

    /**
     * localhost:8800/echo
     *
     * @return
     */
    @GetMapping("/echo")
    public String echo() {
        return "service1";
    }

    /**
     * localhost:8800/echoService1/service1
     *
     * @param content
     * @return
     */
    @RequestMapping("/echoService1/{content}")
    public String echoService1(@PathVariable String content) {
        return "service1 return " + service2Client.echoService2(content);
    }

    /**
     * localhost:8800/upload
     *
     * MultipartAutoConfiguration
     *
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    public String upload(@RequestParam MultipartFile file, HttpServletRequest request) {
        log.debug("calling MicroService1 upload");

        log.debug("request.getContentType: {}", request.getContentType());
        String originalFilename = file.getOriginalFilename();
        log.debug("originalFilename: {}", originalFilename);

        try {
            assert originalFilename != null;
            file.transferTo(new File(originalFilename));
        } catch (IOException e) {
            e.printStackTrace();

            return "upload file error";
        }

        return "upload file success";
    }

}
