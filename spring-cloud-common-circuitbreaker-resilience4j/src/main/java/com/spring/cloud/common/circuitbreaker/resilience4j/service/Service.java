package com.spring.cloud.common.circuitbreaker.resilience4j.service;

import java.util.concurrent.CompletableFuture;

public interface Service {
    String failure(String name);

//    String failureWithFallback();
//
//    String success();
//
//    String successException();
//
//    String ignoreException();

//    Flux<String> fluxSuccess();
//
//    Flux<String> fluxFailure();
//
//    Flux<String> fluxTimeout();
//
//    Mono<String> monoSuccess();
//
//    Mono<String> monoFailure();
//
//    Mono<String> monoTimeout();

//    CompletableFuture<String> futureSuccess();

    CompletableFuture<String> futureFailure(Integer age);

//    CompletableFuture<String> futureTimeout();

}
