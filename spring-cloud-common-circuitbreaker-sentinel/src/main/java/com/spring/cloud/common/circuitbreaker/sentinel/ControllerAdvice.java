package com.spring.cloud.common.circuitbreaker.sentinel;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public String errorHandler(Exception e) {
        if (e instanceof FlowException) {
            log.error("触发限流 .......");
            return "系统繁忙，请稍后再试！";
        }

        if (e instanceof DegradeException) {
            log.debug("触发降级 ......");
            return "系统异常，请稍后再试！";
        }

        e.printStackTrace();

        return "系统出现异常";
    }

}
