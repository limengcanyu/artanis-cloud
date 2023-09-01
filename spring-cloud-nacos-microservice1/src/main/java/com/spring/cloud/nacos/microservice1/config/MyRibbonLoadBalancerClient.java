package com.spring.cloud.nacos.microservice1.config;

import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;

public class MyRibbonLoadBalancerClient extends RibbonLoadBalancerClient {

    private SpringClientFactory clientFactory;

    public MyRibbonLoadBalancerClient(SpringClientFactory clientFactory) {
        super(clientFactory);
        this.clientFactory = clientFactory;
    }

}
