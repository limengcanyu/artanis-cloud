package com.spring.cloud.micro.service1.controller;

import com.spring.cloud.micro.service1.feign.StoreClient;
import com.spring.cloud.micro.service1.service.SyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
@RefreshScope
@RestController
public class MicroService1Controller {
    @Autowired
    private StoreClient storeClient;

    @Autowired
    private SyncService syncService;

    /**
     * http://localhost:8081/getName?name=王三
     *
     * @return
     */
    @GetMapping("/getName")
    public String getName(String name) {
        log.debug("MicroService1Controller getName called ======================");

        try {
            return syncService.getName();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * http://localhost:8081/getStores
     *
     * @return
     */
    @PostMapping("/getStores")
    public String getStores(@RequestBody Map<String, Object> map) {
        return storeClient.getStores(map);
    }

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    /**
     * http://localhost:8081/config/get
     *
     * Nacos配置中心配置以下内容：
     * useLocalCache: true
     *
     * @return
     */
    @RequestMapping("/config/get")
    public boolean get() {
        return useLocalCache;
    }
}
