package com.spring.cloud.micro.service2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class MicroService2Controller {

    /**
     * http://localhost:8082/getName
     *
     * @return
     */
    @RequestMapping("/getName")
    public String getName() {
        return "jessica";
    }

    /**
     * http://localhost:8082/getStores
     *
     * @return
     */
    @PostMapping("/getStores")
    public String getStores(@RequestBody Map<String, Object> map) {
//        String userId = ThreadContextUtil.getUser().getUserId();
//        String username = ThreadContextUtil.getUser().getUsername();
//        log.debug("userId: {} username: {}", userId, username);

        map.put("service2", "==========");
        return map.toString();
    }

}
