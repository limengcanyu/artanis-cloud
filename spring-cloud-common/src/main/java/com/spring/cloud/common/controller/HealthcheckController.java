package com.spring.cloud.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthcheckController {

    /**
     * http://localhost:8080/healthcheck?format=short
     * http://localhost:8080/healthcheck?format=full
     * http://localhost:8080/healthcheck?format=11
     *
     * @param format
     * @return
     */
    @GetMapping(value = "/healthcheck")
    public ResponseEntity<Map<String, Object>> healthcheck(@RequestParam("format") String format) {
        Map<String, Object> map = new HashMap<>();

        if ("short".equals(format)) {
            map.put("status", "OK");
            return ResponseEntity.ok().body(map);
        }

        if ("full".equals(format)) {
            map.put("currentTime", ZonedDateTime.now());
            map.put("status", "OK");
            return ResponseEntity.ok().body(map);
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/healthcheck")
    public ResponseEntity<?> healthcheckPut() {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PostMapping(value = "/healthcheck")
    public ResponseEntity<?>  healthcheckPost() {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }


    @DeleteMapping(value = "/healthcheck")
    public ResponseEntity<?>  healthcheckDelete() {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

}
