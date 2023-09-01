package com.spring.cloud.order.service.mapper;

import com.spring.cloud.order.service.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rock
 * @since 2022-07-29
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {

}
