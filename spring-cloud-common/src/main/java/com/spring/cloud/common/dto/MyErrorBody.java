package com.spring.cloud.common.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MyErrorBody {
    private int value;
    private String detailMessage;
}
