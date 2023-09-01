package com.spring.cloud.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Configuration
public class NacosRuleConfig {
    // nacos server ip
    private static final String remoteAddress = "localhost:8848";
    // nacos group
    private static final String groupId = "gateway_flow_rule";
    // nacos dataId
    private static final String dataId = "com.alibaba.csp.sentinel.demo.flow.rule";

    @PostConstruct
    public void init() {
        loadRules();
        initBlockHandlers();
    }

    // 从Nacos中加载规则配置
    private static void loadRules() {
        log.info("从Nacos中加载网关规则配置 ......");
        // 限流规则
        ReadableDataSource<String, Set<GatewayFlowRule>> flowRuleDataSource = new NacosDataSource<>(
                remoteAddress, groupId, dataId,
                source -> {
                    log.debug("加载网关限流规则: {}", source);
                    return JSON.parseObject(source, new TypeReference<Set<GatewayFlowRule>>() {
                    });
                }
        );
        GatewayRuleManager.register2Property(flowRuleDataSource.getProperty());
    }

    // 注册限流回调，针对限流请求返回自定义内容
    public void initBlockHandlers() {
        BlockRequestHandler blockRequestHandler = (serverWebExchange, throwable) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("code", "001");
            map.put("message", "系统繁忙，请求限流，请稍后再试！");
            return ServerResponse.status(HttpStatus.OK).
                    contentType(MediaType.APPLICATION_JSON).
                    body(BodyInserters.fromValue(map));
        };
        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }
}
