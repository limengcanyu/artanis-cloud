package com.spring.cloud.common.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.common.mybatis.plus.entity.User;
import com.spring.cloud.common.mybatis.plus.mapper.UserMapper;
import com.spring.cloud.common.mybatis.plus.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
