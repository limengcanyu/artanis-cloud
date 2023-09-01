package com.spring.cloud.alert.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.spring.cloud.alert.service.entity.grafana.Alert;
import com.spring.cloud.alert.service.entity.grafana.Root;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.oap.server.core.alarm.AlarmMessage;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class AlertController {

    /**
     * http://localhost:8085/alertByGrafana
     *
     * @return
     */
    @RequestMapping("/alertByGrafana")
    public String alertByGrafana(@RequestBody Root root) {
        log.debug("call alertByGrafana param:{}", JSON.toJSONString(root));
        log.debug("root.getMessage: {}", root.getMessage());

        List<Alert> alerts = root.getAlerts();
        if (!CollectionUtils.isEmpty(alerts)) {
            alerts.forEach(alert -> log.debug("alert value: {}", alert.getValueString()));
        }

        return "alert ok";
    }


    /**
     * http://localhost:8085/alertBySkyWalking
     *
     * @return
     */
    @RequestMapping("/alertBySkyWalking")
    public String alertBySkyWalking(@RequestBody List<AlarmMessage> alarmMessageList) {
        log.debug("call alertBySkyWalking alarmMessageList:{}", JSONObject.toJSONString(alarmMessageList));
        return "alert ok";
    }

    /**
     * http://localhost:8085/alertByAlertmanager
     *
     * @return
     */
    @RequestMapping("/alertByAlertmanager")
    public String alertByAlertmanager(@RequestBody Map<String, Object> map) {
        log.debug("========= webhook param: {}", JSON.toJSONString(map));
        return "alert ok";
    }
}
