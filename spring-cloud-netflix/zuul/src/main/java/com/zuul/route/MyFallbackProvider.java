package com.zuul.route;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2019/3/20 0020
 */
@Component
public class MyFallbackProvider implements FallbackProvider {
    private static final Logger logger = LoggerFactory.getLogger(MyFallbackProvider.class);

    /**
     * fallback用于哪个路由
     *
     * @return
     */
    @Override
    public String getRoute() {
        logger.debug("------ getRoute ------");

        return "*"; // 服务id，如果需要所有调用都支持回退，则return "*"或return null
    }

    /**
     * 执行失败时提供的fallback响应
     *
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        logger.debug("------ fallbackResponse ------");

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
                logger.debug("------ getStatusCode ------");

                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                logger.debug("------ getRawStatusCode ------");

                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                logger.debug("------ getStatusText ------");

                return status.getReasonPhrase();
            }

            @Override
            public void close() {
                logger.debug("------ close ------");

            }

            @Override
            public InputStream getBody() throws IOException {
                logger.debug("------ getBody ------");

                return new ByteArrayInputStream("fallback".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                logger.debug("------ getHeaders ------");

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
