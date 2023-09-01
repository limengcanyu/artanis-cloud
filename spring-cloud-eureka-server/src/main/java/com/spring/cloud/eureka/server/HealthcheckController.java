package com.spring.cloud.eureka.server;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthcheckController {

    /**
     * http://localhost:8761/healthcheck?format=short
     *
     * @param format
     * @return
     */
    @GetMapping(value = "/healthcheck")
    public Map<String, Object> healthcheck(@RequestParam("format") String format) {
        Map<String, Object> map = new HashMap<>();

        if ("short".equals(format)) {
            map.put("status", "OK");
            return map;
        }

        if ("full".equals(format)) {
            map.put("currentTime", LocalDateTime.now());
            map.put("status", "OK");
            return map;
        }

        return map;
    }

    @PutMapping(value = "/healthcheck")
    public void healthcheckPut() {
        return;
    }

    @PostMapping(value = "/healthcheck")
    public void healthcheckPost() {
        return;
    }


    @DeleteMapping(value = "/healthcheck")
    public void healthcheckDelete() {
        return;
    }

}
