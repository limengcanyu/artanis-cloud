package com.spring.cloud.order.service.service;

import com.spring.cloud.order.service.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rock
 * @since 2022-07-29
 */
public interface OrderService extends IService<Order> {

    void placeOrder(String userId, String commodityCode, Integer count);

    void placePoint(String userId, String pointCode, Integer count);

    void create(String userId, String commodityCode, Integer count);
}
