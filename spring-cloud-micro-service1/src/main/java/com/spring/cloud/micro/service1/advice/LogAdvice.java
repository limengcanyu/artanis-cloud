package com.spring.cloud.micro.service1.advice;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LogAdvice {

    @Pointcut("execution(* com.spring.cloud.micro.service1.controller..*.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();

        StringBuilder sb = new StringBuilder();
        int length = args.length;
        for (int i = 0; i < length; i++) {
            if (null != args[i] && !(args[i] instanceof HttpServletRequest) && !(args[i] instanceof HttpServletResponse) && !(args[i] instanceof MultipartFile)) {
                sb.append("[").append(i).append("]: ").append(args[i]).append("\n");
            }
        }

        log.info("\n开始执行方法: {} \n参数: {}\n", signature, sb);

        try {
            Object result = joinPoint.proceed();
            log.info("\n结束执行方法: {} \n结果: {}\n", signature, JSONObject.toJSONString(result));
            return result;
        } catch (Throwable e) {
            log.info("\n结束执行方法: {} \n异常: ", signature, e);
            throw new RuntimeException(e);
        }
    }

}
