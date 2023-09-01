package com.spring.cloud.nacos.config.client.service.impl;

import com.spring.cloud.nacos.config.client.dao.entity.ExampleUser;
import com.spring.cloud.nacos.config.client.dao.mapper.ExampleUserMapper;
import com.spring.cloud.nacos.config.client.service.ExampleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rock.jiang
 * @since 2020-12-20
 */
@Service
public class ExampleUserServiceImpl extends ServiceImpl<ExampleUserMapper, ExampleUser> implements ExampleUserService {

}
