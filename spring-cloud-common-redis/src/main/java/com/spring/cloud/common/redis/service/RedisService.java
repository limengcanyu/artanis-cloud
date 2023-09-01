package com.spring.cloud.common.redis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cloud.common.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void valueSet(String key, String value) {
        try {
            stringRedisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(value));
        } catch (JsonProcessingException e) {
            log.error("redis valueSet error: ", e);
        }
    }

    public void valueSet(String key, String value, long timeout, TimeUnit unit) {
        try {
            stringRedisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(value), timeout, unit);
        } catch (JsonProcessingException e) {
            log.error("redis valueSet error: ", e);
        }
    }

    public <T> T valueGet(String key, Class<T> clz) {
        try {
            return objectMapper.readValue(stringRedisTemplate.opsForValue().get(key), clz);
        } catch (JsonProcessingException e) {
            log.error("redis valueGet error: ", e);
        }
        return null;
    }

    public User getUser(String userId) {
        return User.builder().userId(userId).username("rock").age(21).build();
    }

    public List<String> getUserRoles(String userId) {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE1");
        roles.add("ROLE2");
        roles.add("ROLE3");
        return roles;
    }
}
