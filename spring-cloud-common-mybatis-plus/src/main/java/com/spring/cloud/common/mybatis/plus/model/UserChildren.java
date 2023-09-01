package com.spring.cloud.common.mybatis.plus.model;

import com.spring.cloud.common.mybatis.plus.entity.Children;
import com.spring.cloud.common.mybatis.plus.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserChildren extends User {

    private List<Children> c;
}
