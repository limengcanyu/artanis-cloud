package com.spring.cloud.common.circuitbreaker.sentinel;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;

/**
 * Nacos config sender for demo.
 *
 * @author Eric Zhao
 */
public class NacosConfigSender {

    public static void main(String[] args) throws Exception {
        final String remoteAddress = "localhost:8848";
        final String groupId = "Sentinel_Demo";
        final String dataId = "com.alibaba.csp.sentinel.demo.flow.rule";
        String rule = "[\n"
                + "  {\n"
                + "    \"resource\": \"sayHello\",\n"
                + "    \"controlBehavior\": 0,\n"
                + "    \"count\": 5.0,\n"
                + "    \"grade\": 1,\n"
                + "    \"limitApp\": \"default\",\n"
                + "    \"strategy\": 0\n"
                + "  }\n"
                + "]";

//        FlowRule flowRule = new FlowRule();
//        flowRule.setResource("sayHello");
//        flowRule.setControlBehavior(0);
//        flowRule.setCount(3.0);
//        flowRule.setGrade(1);
//        flowRule.setLimitApp("default");
//        flowRule.setStrategy(0);
//
//        rule = JSONObject.toJSONString(flowRule);

        ConfigService configService = NacosFactory.createConfigService(remoteAddress);
        System.out.println(configService.publishConfig(dataId, groupId, rule));

    }
}