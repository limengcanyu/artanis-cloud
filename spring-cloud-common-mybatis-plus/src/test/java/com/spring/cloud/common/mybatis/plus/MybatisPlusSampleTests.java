package com.spring.cloud.common.mybatis.plus;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.spring.cloud.common.mybatis.plus.entity.User;
import com.spring.cloud.common.mybatis.plus.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通过 @MybatisPlusTest 可快速编写 Mapper 对应的测试类，实现快速测试代码
 */
@MybatisPlusTest
public class MybatisPlusSampleTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
        User user = new User();
        user.setTenantId(1L);
        user.setName("一一");
        Assertions.assertTrue(userMapper.insert(user) > 0);
        user = userMapper.selectById(user.getId());
        Assertions.assertTrue(1L == user.getTenantId());
    }

}
