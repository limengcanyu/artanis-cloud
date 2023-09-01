package com.spring.cloud.common.mybatis.plus.controller;

import com.spring.cloud.common.mybatis.plus.entity.User;
import com.spring.cloud.common.mybatis.plus.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Resource
    private UserMapper mapper;

    /**
     * http://localhost:8080/aInsert
     *
     * @return
     */
    @RequestMapping("/aInsert")
    public Long aInsert() {
        User user = new User();
        user.setName("一一");
        mapper.insert(user);
        user = mapper.selectById(user.getId());
        return user.getTenantId();
    }
}
