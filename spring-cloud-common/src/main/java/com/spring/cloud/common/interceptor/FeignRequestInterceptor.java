package com.spring.cloud.common.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * Feign消费方设置:
 * Feign请求拦截器
 */
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.debug("============= request url: {}", requestTemplate.url());
        requestTemplate.header("feign-request", "true");
    }
}
