package com.spring.cloud.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Feign提供方设置:
 * Feign调用认证拦截器
 */
@Slf4j
@Component
public class FeignAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        log.info("============ Authorization:" + authorization);
        if (ObjectUtils.isEmpty(authorization)) {
            String access_token = request.getParameter("access_token");
            return !ObjectUtils.isEmpty(access_token);
        }

        return true;
    }
}
