//package com.spring.cloud.common;
//
//import com.spring.cloud.common.entity.User;
//import com.spring.cloud.common.util.service.RedisService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.ValueOperations;
//
//@SpringBootTest
//public class RedisServiceTests {
//    @Autowired
//    private RedisService redisService;
//
//    @Autowired
//    private ValueOperations<String, Object> valueOperation;
//
//    @Test
//    public void valueSet() {
//        redisService.valueSet("tenantId", "T0001");
//        System.out.println(redisService.valueGet("tenantId"));
//
//        redisService.valueSet("user1", User.builder().userId("U001").username("rock").build());
//        System.out.println(redisService.valueGet("user1"));
//
//
//        valueOperation.set("companyId", "C0001");
//        System.out.println(valueOperation.get("companyId"));
//
//        valueOperation.set("user2", User.builder().userId("U002").username("jessica").build());
//        System.out.println(valueOperation.get("user2"));
//
//    }
//
//}
