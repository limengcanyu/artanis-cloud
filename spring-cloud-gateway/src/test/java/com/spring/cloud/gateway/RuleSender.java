package com.spring.cloud.gateway;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayParamFlowItem;
import com.alibaba.fastjson2.JSON;
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
        final String groupId = "gateway_flow_rule";
        final String dataId = "com.alibaba.csp.sentinel.demo.flow.rule";

        String rule = """
                [
                  {
                    "resource": "aliyun_route",
                    "resourceMode": 0,
                    "grade": 1,
                    "count": 5.0,
                    "intervalSec": 1,
                    "controlBehavior": 0,
                    "burst": 1,
                    "maxQueueingTimeoutMs": 1000
                  }
                ]
                """;

//        GatewayFlowRule flowRule = new GatewayFlowRule();
//        flowRule.setResource("aliyun_route"); // 资源名称，可以是网关中的 route 名称或者用户自定义的 API 分组名称。
//        flowRule.setResourceMode(0); // 规则是针对 API Gateway 的 route（RESOURCE_MODE_ROUTE_ID）还是用户在 Sentinel 中定义的 API 分组（RESOURCE_MODE_CUSTOM_API_NAME），默认是 route。
//        flowRule.setGrade(1); // 限流指标维度，同限流规则的 grade 字段。
//        flowRule.setCount(5.0); // 限流阈值
//        flowRule.setIntervalSec(1); // 统计时间窗口，单位是秒，默认是 1 秒。
//        flowRule.setControlBehavior(0); // 流量整形的控制效果，同限流规则的 controlBehavior 字段，目前支持快速失败和匀速排队两种模式，默认是快速失败。
//        flowRule.setBurst(1); // 应对突发请求时额外允许的请求数目。
//        flowRule.setMaxQueueingTimeoutMs(1000); // 匀速排队模式下的最长排队时间，单位是毫秒，仅在匀速排队模式下生效。
//        GatewayParamFlowItem item = new GatewayParamFlowItem();
//        flowRule.setParamItem(item); // 参数限流配置。若不提供，则代表不针对参数进行限流，该网关规则将会被转换成普通流控规则；否则会转换成热点规则。
//
//        rule = JSON.toJSONString(flowRule);

        ConfigService configService = NacosFactory.createConfigService(remoteAddress);
        System.out.println(configService.publishConfig(dataId, groupId, rule));
    }

}
