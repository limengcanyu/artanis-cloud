package com.spring.cloud.common.circuitbreaker.resilience4j.controller;

import com.spring.cloud.common.circuitbreaker.resilience4j.service.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/backendA")
public class BackendAController {

    private final Service businessAService;

    public BackendAController(@Qualifier("backendAService") Service businessAService){
        this.businessAService = businessAService;
    }

    /**
     * http://localhost:8080/backendA/failure
     *
     * @return
     */
    @GetMapping("failure")
    public String failure(){
        return businessAService.failure("rock");
    }

//    @GetMapping("success")
//    public String success(){
//        return businessAService.success();
//    }
//
//    @GetMapping("successException")
//    public String successException(){
//        return businessAService.successException();
//    }
//
//    @GetMapping("ignore")
//    public String ignore(){
//        return businessAService.ignoreException();
//    }
//
//    @GetMapping("monoSuccess")
//    public Mono<String> monoSuccess(){
//        return businessAService.monoSuccess();
//    }
//
//    @GetMapping("monoFailure")
//    public Mono<String> monoFailure(){
//        return businessAService.monoFailure();
//    }
//
//    @GetMapping("fluxSuccess")
//    public Flux<String> fluxSuccess(){
//        return businessAService.fluxSuccess();
//    }
//
//    @GetMapping("monoTimeout")
//    public Mono<String> monoTimeout(){
//        return businessAService.monoTimeout();
//    }
//
//    @GetMapping("fluxTimeout")
//    public Flux<String> fluxTimeout(){
//        return businessAService.fluxTimeout();
//    }

    /**
     * http://localhost:8080/backendA/futureFailure
     *
     * @return
     */
    @GetMapping("futureFailure")
    public CompletableFuture<String> futureFailure(){
        return businessAService.futureFailure(21);
    }

//    @GetMapping("futureSuccess")
//    public CompletableFuture<String> futureSuccess(){
//        return businessAService.futureSuccess();
//    }
//
//    @GetMapping("futureTimeout")
//    public CompletableFuture<String> futureTimeout(){
//        return businessAService.futureTimeout();
//    }
//
//    @GetMapping("fluxFailure")
//    public Flux<String> fluxFailure(){
//        return businessAService.fluxFailure();
//    }
//
//    @GetMapping("fallback")
//    public String failureWithFallback(){
//        return businessAService.failureWithFallback();
//    }
}
