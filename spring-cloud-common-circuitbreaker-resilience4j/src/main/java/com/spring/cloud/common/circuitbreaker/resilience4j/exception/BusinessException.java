package com.spring.cloud.common.circuitbreaker.resilience4j.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
