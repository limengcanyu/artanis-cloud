package com.zuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2019/3/20 0020
 */
@EnableZuulProxy
@SpringBootApplication
public class ZuulApplication {
    private static final Logger logger = LoggerFactory.getLogger(ZuulApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
        logger.info("Zuul started ...");
    }
}
