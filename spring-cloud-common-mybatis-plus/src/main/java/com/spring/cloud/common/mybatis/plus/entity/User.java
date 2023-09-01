package com.spring.cloud.common.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class User {
    private Long id;
    private Long tenantId;
    private String name;
    private Integer age;
    private String email;

    @TableField(exist = false)
    private String addrName;
}
