package com.spring.cloud.alert.service.entity.grafana;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Root {
    private String receiver;
    private String status;
    private List<Alert> alerts = new ArrayList<>();
    private GroupLabels groupLabels;
    private CommonLabels commonLabels;
    private CommonAnnotations commonAnnotations;
    private String externalURL;
    private String version;
    private String groupKey;
    private Integer truncatedAlerts;
    private Integer orgId;
    private String title;
    private String state;
    private String message;
}
