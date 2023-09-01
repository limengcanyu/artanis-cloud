package com.spring.cloud.micro.service1.async.service;

import java.util.concurrent.CompletableFuture;

public interface AsyncService {
    CompletableFuture<String> getName();
}
