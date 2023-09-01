package com.spring.cloud.zuul.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulErrorFilter;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulPostFilter;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulPreFilter;
import com.netflix.zuul.ZuulFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * Route ID 分组
 */
@Slf4j
@Configuration
public class ZuulConfig {
    /*
       以下3个ZuulFilter已经存在，若要覆盖，则需配置 spring.main.allow-bean-definition-overriding: true
     */

//    // 底层继承了 ZuulFilter
//    @Bean
//    public ZuulFilter sentinelZuulPreFilter() {
//        // We can also provider the filter order in the constructor.
//        return new SentinelZuulPreFilter(10000);
//    }
//
//    // 底层继承了 ZuulFilter
//    @Bean
//    public ZuulFilter sentinelZuulPostFilter() {
//        return new SentinelZuulPostFilter(10000);
//    }
//
//    // 底层继承了 ZuulFilter
//    @Bean
//    public ZuulFilter sentinelZuulErrorFilter() {
//        return new SentinelZuulErrorFilter(-1);
//    }

    /**
     * 依赖注入完成之后执行初始化
     */
    @PostConstruct
    public void doInit() {
        log.debug("=== 加载网关限流规则");

        // 注册 FallbackProvider
        ZuulBlockFallbackManager.registerProvider(new Microservice1BlockFallbackProvider());

        // 加载网关限流规则
        initGatewayRules();
    }

    /**
     * 网关限流规则
     */
    private void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        /*
            resource：资源名称，可以是网关中的 route 名称或者用户自定义的 API 分组名称
            count：限流阈值
            intervalSec：统计时间窗口，单位是秒，默认是 1 秒
         */
        rules.add(new GatewayFlowRule("spring-cloud-microservice1")
                .setCount(1) // 限流阈值
                .setIntervalSec(1)); // 统计时间窗口，单位是秒，默认是 1 秒

        // 加载网关限流规则
        GatewayRuleManager.loadRules(rules);
    }
}
