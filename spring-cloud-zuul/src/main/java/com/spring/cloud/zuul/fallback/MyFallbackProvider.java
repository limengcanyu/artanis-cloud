package com.spring.cloud.zuul.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: Providing Hystrix Fallbacks For Routes </p>
 *
 * 测试 Fallback：在 microservice1 启动期间调用 microservice1 的服务
 *
 * @author rock.jiang
 * Date 2020/04/19 13:31
 */
@Slf4j
@Component
public class MyFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
//        return "spring-cloud-microservice1"; // specify the route ID the fallback is for， zuul.routes.spring-cloud-microservice1: /microservice1/**
        return "*"; // provide a default fallback for all routes
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        log.debug("====== fallbackResponse");
        cause.printStackTrace();

        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            /**
             * 返回 fallback 内容
             *
             * @return
             * @throws IOException
             */
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("系统异常，请稍后重试！".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
