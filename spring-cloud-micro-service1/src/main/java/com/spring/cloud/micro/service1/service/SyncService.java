package com.spring.cloud.micro.service1.service;

import java.util.concurrent.ExecutionException;

public interface SyncService {
    String getName() throws ExecutionException, InterruptedException;
}
