package com.spring.cloud.micro.service1.async.service.impl;

import com.spring.cloud.micro.service1.async.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Async
@Service
public class AsyncServiceImpl implements AsyncService {
    @Override
    public CompletableFuture<String> getName() {
        log.debug("AsyncServiceImpl currentThread id: {} name: {}", Thread.currentThread().getId(), Thread.currentThread().getName());
        return CompletableFuture.completedFuture("async result");
    }
}
