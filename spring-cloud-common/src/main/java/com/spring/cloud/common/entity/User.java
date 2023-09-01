package com.spring.cloud.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    private String tenantId;
    private String companyId;
    private String userId;
    private String username;
    private String password;
    private int age;
}
