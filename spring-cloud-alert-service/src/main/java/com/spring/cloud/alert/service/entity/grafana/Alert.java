package com.spring.cloud.alert.service.entity.grafana;

import lombok.Data;

@Data
public class Alert {
    private String status;
    private Labels labels;
    private Annotations annotations;
    private String startsAt;
    private String endsAt;
    private String generatorURL;
    private String fingerprint;
    private String silenceURL;
    private String dashboardURL;
    private String panelURL;
    private String valueString;
}
