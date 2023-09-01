package com.spring.cloud.common.controller;

import com.spring.cloud.common.annotation.RolePermission;
import com.spring.cloud.common.util.ThreadContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class SampleController {
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * http://localhost:8080/getBean
     *
     * @return
     */
    @RequestMapping("/getBean")
    public String getBean() {
        SampleController bean = applicationContext.getBean(SampleController.class);
        return bean.toString();
    }

    /**
     * http://localhost:8080/getUser
     *
     * @return
     */
    @RequestMapping("/getUser")
    public String getUser() {
        return ThreadContextUtil.getUser().getUserId();
    }

    /**
     * http://localhost:8081/hello
     * http://localhost:8082/hello
     * http://localhost:8083/hello
     * <p>
     * gateway:
     * http://localhost:8091/service1/hello
     *
     * @return
     */
    @RolePermission(required = false, roles = {"ROLE1", "ROLE2"})
    @RequestMapping("/hello")
    public String hello() {
        return "Hello";
    }

    /**
     * http://localhost:8081/getMap
     * <p>
     * gateway:
     * http://localhost:8091/service1/getMap
     *
     * @param map
     * @return
     */
    @RequestMapping("/getMap")
    public Map<String, Object> getMap(@RequestBody Map<String, Object> map) {
        String userId = ThreadContextUtil.getUser().getUserId();
        String userName = ThreadContextUtil.getUser().getUsername();

        log.debug("userId: {} userName: {} getMap param: {}", userId, userName, map);
        map.put("add", 123);
        return map;
    }

}
