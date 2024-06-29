package com.spring.cloud.common.circuitbreaker.sentinel;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreaker;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.EventObserverRegistry;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.util.TimeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

@Slf4j
@Configuration
public class NacosRuleConfig {
    // nacos server ip
    private static final String remoteAddress = "localhost:8848";
    // nacos group
    private static final String groupId = "Sentinel_Demo";
    // nacos dataId
    private static final String dataId = "com.alibaba.csp.sentinel.demo.flow.rule";
    private static final String dataId2 = "com.alibaba.csp.sentinel.demo.degrade.rule";

    @PostConstruct
    public void init() {
        registerStateChangeObserver();
        loadRules();
    }

    // 熔断器事件监听 自定义的事件监听器监听熔断器状态变换事件（state change event）
    private static void registerStateChangeObserver() {
        log.info("注册 熔断器事件监听 ......");
        EventObserverRegistry.getInstance().addStateChangeObserver("logging",
                (prevState, newState, rule, snapshotValue) -> {
                    if (newState == CircuitBreaker.State.OPEN) {
                        log.debug("触发熔断，熔断器开启 ......");
                        log.error("{} -> OPEN at {}, snapshotValue={}", prevState.name(), new Date(TimeUtil.currentTimeMillis()), snapshotValue);
                    } else {
                        log.error("{} -> {} at {}", prevState.name(), newState.name(), new Date(TimeUtil.currentTimeMillis()));
                    }
                });
    }

    // 从Nacos中加载规则配置
    private static void loadRules() {
        log.info("从Nacos中加载规则配置 ......");
        // 限流规则
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(
                remoteAddress, groupId, dataId,
                source -> {
                    log.debug("加载限流规则: {}", source);
                    return JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                    });
                }
        );
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());

        // 熔断降级规则
        ReadableDataSource<String, List<DegradeRule>> degradeRuleDataSource = new NacosDataSource<>(
                remoteAddress, groupId, dataId2,
                source -> {
                    log.debug("加载熔断降级规则: {}", source);
                    return JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {
                    });
                }
        );
        DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());
    }

}
