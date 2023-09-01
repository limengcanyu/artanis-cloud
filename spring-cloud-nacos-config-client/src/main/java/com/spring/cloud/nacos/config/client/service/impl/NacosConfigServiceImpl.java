package com.spring.cloud.nacos.config.client.service.impl;

import com.spring.cloud.nacos.config.client.config.OrderProperties;
import com.spring.cloud.nacos.config.client.service.NacosConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@RefreshScope
@Service
public class NacosConfigServiceImpl implements NacosConfigService {

    @Resource
    private OrderProperties orderProperties;

    @Value("${useLocalCache:false}")
    private String useLocalCache;

    @Value("${product.id}")
    private String productId;

    @Value("${order.password}")
    private String password;

    @Override
    public String getConfig() {
        String result = "";

        result += " productId: " + productId + " useLocalCache: " + useLocalCache + " password: " + password;
        result += " order id: " + orderProperties.getId() + " password: " + orderProperties.getPassword();

        return result;
    }
}
