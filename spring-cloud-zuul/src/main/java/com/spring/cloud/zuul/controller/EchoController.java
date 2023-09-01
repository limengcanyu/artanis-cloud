package com.spring.cloud.zuul.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2020/01/03 10:45
 */
@RestController
public class EchoController {

    /**
     * http://localhost:8790/echo
     *
     * @return
     */
    @RequestMapping("/echo")
    public String echo() {
        return "this is zuul echo";
    }
}
