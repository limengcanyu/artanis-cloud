//package com.spring.cloud.user.service.service.impl;
//
//import com.alibaba.csp.sentinel.annotation.SentinelResource;
//import com.alibaba.csp.sentinel.slots.block.BlockException;
//import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
//import com.alibaba.nacos.common.utils.ExceptionUtil;
//import com.spring.cloud.user.service.feign.OrderClient;
//import com.spring.cloud.user.service.service.SentinelService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Service
//public class SentinelServiceImpl implements SentinelService {
//
//    @Autowired
//    private OrderClient orderClient;
//
//    /**
//     * <a href="https://github.com/alibaba/Sentinel/wiki/%E6%B3%A8%E8%A7%A3%E6%94%AF%E6%8C%81">https://github.com/alibaba/Sentinel/wiki/%E6%B3%A8%E8%A7%A3%E6%94%AF%E6%8C%81</a>
//     * <p>
//     * 限流调用优先级：blockHandler > fallback
//     *
//     * @param name
//     * @return
//     */
//    @SentinelResource(value = "sayHello", blockHandler = "blockHandlerForSayHello")
//    @Override
//    public String sayHello(String name) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("userId", "U0001");
//
//        return orderClient.getUserOrders(map);
//    }
//
//    // blockHandler 函数会在原方法被限流/降级/系统保护的时候调用，而 fallback 函数会针对所有类型的异常。
//    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
//    public String blockHandlerForSayHello(String name, BlockException ex) {
//        if (FlowException.isBlockException(ex)) {
//            log.debug("触发 流控规则 name: {} ex: ", name, ex);
//            return "流量过大，触发流量控制！";
//        }
//
//        if (DegradeException.isBlockException(ex)) {
//            log.debug("触发 降级规则 name: {} ex: ", name, ex);
//            return "服务调用超时，触发降级控制！";
//        }
//        return "blockHandlerForSayHello: " + name;
//    }
//
//    // 这里单独演示 blockHandlerClass 的配置.
//    // 对应的 `handleException` 函数需要位于 `ExceptionUtil` 类中，并且必须为 public static 函数.
//    @SentinelResource(value = "test", blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
//    public void test() {
//        System.out.println("Test");
//    }
//
//}
