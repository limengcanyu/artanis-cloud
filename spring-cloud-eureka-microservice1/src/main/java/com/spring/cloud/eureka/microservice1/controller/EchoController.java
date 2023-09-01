package com.spring.cloud.eureka.microservice1.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2019/12/25 10:57
 */
@Slf4j
@RestController
public class EchoController {

    @GetMapping("/")
    public String home() {
        log.debug("calling MicroService1 home");
        return "MicroService1 home";
    }

    /**
     * localhost:8800/echo/1212
     *
     * @param string
     * @return
     */
    @RequestMapping("/echo/{string}")
    public String echo(@PathVariable String string) {
        log.debug("calling MicroService1 echo");
        return "MicroService1 echo " + string;
    }

    /**
     * localhost:8800/echoParam?user=1212
     *
     * @param user
     * @return
     */
    @RequestMapping("/echoParam")
    public String echoParam(@RequestParam String user) {
        log.debug("calling MicroService1 echoParam");
        return "MicroService1 echoParam " + user;
    }

    @RequestMapping("/echo2/{string}")
    public String echo2(@PathVariable String string) {
        log.debug("calling MicroService1 echo2");
        return "MicroService1 echo2 " + string;
    }

    @RequestMapping("/modifyRequestBody")
    public String modifyRequestBody(@RequestBody Map<String, Object> param) {
        log.debug("calling MicroService1 modifyRequestBody");
        return "MicroService1 modifyRequestBody " + param;
    }

    @RequestMapping("/save")
    public String save(@RequestBody JSONObject routeJsonObject) {
        log.debug("calling MicroService1 save");
        return "MicroService1 save " + JSONObject.toJSONString(routeJsonObject);
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
