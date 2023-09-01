package com.spring.cloud.eureka.microservice1.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/7/19 18:44
 */
@RestController
public class EurekaClientController {
    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient discoveryClient;

    /**
     * localhost:8800/eurekaClient/serviceUrl
     *
     * @return
     */
    @RequestMapping("/eurekaClient/serviceUrl")
    public String serviceUrl() {
        // 根据服务名称获取服务实例注册信息
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("spring-cloud-eureka-microservice1", false);
        return instance.getHomePageUrl();
    }
}
