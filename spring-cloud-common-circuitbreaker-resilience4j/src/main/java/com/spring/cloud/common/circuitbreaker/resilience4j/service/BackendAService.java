package com.spring.cloud.common.circuitbreaker.resilience4j.service;


import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * This Service shows how to use the CircuitBreaker annotation.
 */
@Slf4j
@Component(value = "backendAService")
public class BackendAService implements Service {

    private static final String BACKEND_A = "backendA";

    @Override
    @CircuitBreaker(name = BACKEND_A, fallbackMethod = "failureCircuitBreakerFallback")
    @Bulkhead(name = BACKEND_A, fallbackMethod = "failureBulkheadFallback")
    @Retry(name = BACKEND_A, fallbackMethod = "failureRetryFallback")
    public String failure(String name) {
        log.debug("call failure ......");
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "This is a remote exception");
    }

    public String failureBulkheadFallback(String name, Throwable t) {
        return "bulkhead failure result ===> hello " + t.getMessage();
    }

    public String failureCircuitBreakerFallback(String name, Throwable t) {
        return "circuit breaker failure result ===> hello " + t.getMessage();
    }

    /**
     * 重试失败回调
     *
     * @param name
     * @param t
     * @return
     */
    public String failureRetryFallback(String name, Throwable t) {
        return "retry failure result ===> hello " + t.getMessage();
    }

//    @Override
//    @CircuitBreaker(name = BACKEND_A)
//    @Bulkhead(name = BACKEND_A)
//    public String ignoreException() {
//        throw new BusinessException("This exception is ignored by the CircuitBreaker of backend A");
//    }
//
//    @Override
//    @CircuitBreaker(name = BACKEND_A)
//    @Bulkhead(name = BACKEND_A)
//    @Retry(name = BACKEND_A)
//    public String success() {
//        return "Hello World from backend A";
//    }
//
//    @Override
//    @CircuitBreaker(name = BACKEND_A)
//    @Bulkhead(name = BACKEND_A)
//    public String successException() {
//        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "This is a remote client exception");
//    }
//
//    @Override
//    @CircuitBreaker(name = BACKEND_A)
//    @Bulkhead(name = BACKEND_A)
//    @Retry(name = BACKEND_A)
//    public Flux<String> fluxFailure() {
//        return Flux.error(new IOException("BAM!"));
//    }
//
//    @Override
//    @TimeLimiter(name = BACKEND_A)
//    @CircuitBreaker(name = BACKEND_A, fallbackMethod = "fluxFallback")
//    public Flux<String> fluxTimeout() {
//        return Flux.
//                just("Hello World from backend A")
//                .delayElements(Duration.ofSeconds(10));
//    }
//
//    @Override
//    @TimeLimiter(name = BACKEND_A)
//    @CircuitBreaker(name = BACKEND_A)
//    @Bulkhead(name = BACKEND_A)
//    @Retry(name = BACKEND_A)
//    public Mono<String> monoSuccess() {
//        return Mono.just("Hello World from backend A");
//    }
//
//    @Override
//    @CircuitBreaker(name = BACKEND_A)
//    @Bulkhead(name = BACKEND_A)
//    @Retry(name = BACKEND_A)
//    public Mono<String> monoFailure() {
//        return Mono.error(new IOException("BAM!"));
//    }
//
//    @Override
//    @TimeLimiter(name = BACKEND_A)
//    @Bulkhead(name = BACKEND_A)
//    @CircuitBreaker(name = BACKEND_A, fallbackMethod = "monoFallback")
//    public Mono<String> monoTimeout() {
//        return Mono.just("Hello World from backend A")
//                .delayElement(Duration.ofSeconds(10));
//    }
//
//    @Override
//    @TimeLimiter(name = BACKEND_A)
//    @CircuitBreaker(name = BACKEND_A)
//    @Retry(name = BACKEND_A)
//    public Flux<String> fluxSuccess() {
//        return Flux.just("Hello", "World");
//    }
//
//    @Override
//    @CircuitBreaker(name = BACKEND_A, fallbackMethod = "fallback")
//    public String failureWithFallback() {
//        return failure();
//    }
//
//    @Override
//    @Bulkhead(name = BACKEND_A, type = Type.THREADPOOL)
//    @TimeLimiter(name = BACKEND_A)
//    @CircuitBreaker(name = BACKEND_A)
//    @Retry(name = BACKEND_A)
//    public CompletableFuture<String> futureSuccess() {
//        return CompletableFuture.completedFuture("Hello World from backend A");
//    }

    @Override
    @Bulkhead(name = BACKEND_A, type = Bulkhead.Type.THREADPOOL, fallbackMethod = "futureBulkheadFallback")
    @TimeLimiter(name = BACKEND_A)
    @CircuitBreaker(name = BACKEND_A)
    @Retry(name = BACKEND_A)
    public CompletableFuture<String> futureFailure(Integer age) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        CompletableFuture<String> future = new CompletableFuture<>();
        future.completeExceptionally(new IOException("BAM!"));
        return future;
    }

    private CompletableFuture<String> futureBulkheadFallback(Integer age, IOException ex) {
        return CompletableFuture.completedFuture("age: " + age + " Recovered specific IOException: " + ex.toString());
    }

//    @Override
//    @Bulkhead(name = BACKEND_A, type = Type.THREADPOOL)
//    @TimeLimiter(name = BACKEND_A)
//    @CircuitBreaker(name = BACKEND_A, fallbackMethod = "futureFallback")
//    public CompletableFuture<String> futureTimeout() {
//        Try.run(() -> Thread.sleep(5000));
//        return CompletableFuture.completedFuture("Hello World from backend A");
//    }
//
//    private String fallback(HttpServerErrorException ex) {
//        return "Recovered HttpServerErrorException: " + ex.getMessage();
//    }
//
//    private String fallback(Exception ex) {
//        return "Recovered: " + ex.toString();
//    }
//
//    private CompletableFuture<String> futureFallback(TimeoutException ex) {
//        return CompletableFuture.completedFuture("Recovered specific TimeoutException: " + ex.toString());
//    }
//
//    private CompletableFuture<String> futureFallback(BulkheadFullException ex) {
//        return CompletableFuture.completedFuture("Recovered specific BulkheadFullException: " + ex.toString());
//    }
//
//    private CompletableFuture<String> futureFallback(CallNotPermittedException ex) {
//        return CompletableFuture.completedFuture("Recovered specific CallNotPermittedException: " + ex.toString());
//    }
//
//    private Mono<String> monoFallback(Exception ex) {
//        return Mono.just("Recovered: " + ex.toString());
//    }
//
//    private Flux<String> fluxFallback(Exception ex) {
//        return Flux.just("Recovered: " + ex.toString());
//    }

}
