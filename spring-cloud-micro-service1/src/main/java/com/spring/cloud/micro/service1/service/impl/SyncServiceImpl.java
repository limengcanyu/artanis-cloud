package com.spring.cloud.micro.service1.service.impl;

import com.spring.cloud.micro.service1.async.service.AsyncService;
import com.spring.cloud.micro.service1.service.SyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class SyncServiceImpl implements SyncService {
    @Autowired
    private AsyncService asyncService;

    @Override
    public String getName() throws ExecutionException, InterruptedException {
        log.debug("SyncServiceImpl currentThread id: {} name: {}", Thread.currentThread().getId(), Thread.currentThread().getName());
        return asyncService.getName().get();
    }
}
