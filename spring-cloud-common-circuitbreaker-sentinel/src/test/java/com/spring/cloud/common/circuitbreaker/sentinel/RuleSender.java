package com.spring.cloud.common.circuitbreaker.sentinel;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 规则推送
 */
@Slf4j
public class RuleSender {

    @Test
    void flowRule() throws NacosException {
        final String remoteAddress = "localhost:8848";
        final String groupId = "Sentinel_Demo";
        final String dataId = "com.alibaba.csp.sentinel.demo.flow.rule";

        String rule = """
                [
                  {
                    "resource": "sayHello",
                    "controlBehavior": 0,
                    "count": 5.0,
                    "grade": 1,
                    "limitApp": "default",
                    "strategy": 0
                  }
                ]
                """;

//        FlowRule flowRule = new FlowRule();
//        flowRule.setResource("sayHello"); // 资源名，即限流规则的作用对象
//        flowRule.setControlBehavior(0); // 流量控制效果（直接拒绝、Warm Up、匀速排队）
//        flowRule.setCount(3.0); // 限流阈值
//        flowRule.setGrade(1); // 限流阈值类型（QPS 或并发线程数）
//        flowRule.setLimitApp("default"); // 流控针对的调用来源，若为 default 则不区分调用来源
//        flowRule.setStrategy(0); // 调用关系限流策略
//
//        rule = JSONObject.toJSONString(flowRule);

        ConfigService configService = NacosFactory.createConfigService(remoteAddress);
        System.out.println(configService.publishConfig(dataId, groupId, rule));
    }

    @Test
    void degradeRule() throws NacosException {
        final String remoteAddress = "localhost:8848";
        final String groupId = "Sentinel_Demo";
        final String dataId = "com.alibaba.csp.sentinel.demo.degrade.rule";

        String rule = """
                [
                  {
                    "resource": "sayHello",
                    "grade": 0,
                    "count": 1000.0,
                    "timeWindow": 2,
                    "minRequestAmount": 5,
                    "statIntervalMs": 1000,
                    "slowRatioThreshold": 1.0
                  }
                ]
                """;

        // 这种方式会出现 com.alibaba.fastjson2.JSONException
//        DegradeRule degradeRule = new DegradeRule();
//        degradeRule.setResource("sayHello"); // 资源名
//        degradeRule.setGrade(0); // 熔断策略，支持0:慢调用比例/1:异常比例/2:异常数策略 默认 慢调用比例
//        degradeRule.setCount(2000.0); // 慢调用比例模式下为慢调用临界 RT（超出该值计为慢调用）；异常比例/异常数模式下为对应的阈值
//        degradeRule.setTimeWindow(2); // 熔断时长，单位为 s
//        degradeRule.setMinRequestAmount(5); // 熔断触发的最小请求数，请求数小于该值时即使异常比率超出阈值也不会熔断（1.7.0 引入） 默认 5
//        degradeRule.setStatIntervalMs(1000); // 统计时长（单位为 ms），如 60*1000 代表分钟级（1.8.0 引入） 默认 1000 ms
//        degradeRule.setSlowRatioThreshold(1.0); // 慢调用比例阈值，仅慢调用比例模式有效（1.8.0 引入）
//
//        rule = JSON.toJSONString(degradeRule);
//        log.debug("熔断降级规则: {}", rule);

        ConfigService configService = NacosFactory.createConfigService(remoteAddress);
        System.out.println(configService.publishConfig(dataId, groupId, rule));
    }
}
