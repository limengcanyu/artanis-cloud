package com.spring.cloud.common.circuitbreaker.resilience4j.controller;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(value = {CallNotPermittedException.class})
    public ResponseEntity<Object> handleAllExceptions(CallNotPermittedException ex) {
        // 自定义错误信息或日志记录
        return new ResponseEntity<>("断路器异常: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
