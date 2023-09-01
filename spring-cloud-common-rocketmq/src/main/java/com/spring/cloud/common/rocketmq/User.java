package com.spring.cloud.common.rocketmq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 需要无参构造方法才能将消息反序列化为对象
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    private String userName;
    private Byte userAge;
}
