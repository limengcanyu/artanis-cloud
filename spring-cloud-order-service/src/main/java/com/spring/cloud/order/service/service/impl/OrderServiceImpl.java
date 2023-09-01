package com.spring.cloud.order.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.order.service.entity.Order;
import com.spring.cloud.order.service.feign.AccountFeignClient;
import com.spring.cloud.order.service.feign.PointFeignClient;
import com.spring.cloud.order.service.feign.StockFeignClient;
import com.spring.cloud.order.service.mapper.OrderMapper;
import com.spring.cloud.order.service.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rock
 * @since 2022-07-29
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private AccountFeignClient accountFeignClient;

    @Autowired
    private StockFeignClient stockFeignClient;

    @Autowired
    private PointFeignClient pointFeignClient;

    @Autowired
    private OrderMapper orderMapper;

    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void placeOrder(String userId, String commodityCode, Integer count) {
        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));
        Order order = new Order().setUserId(userId).setCommodityCode(commodityCode).setCount(count).setMoney(orderMoney);
        orderMapper.insert(order);
        stockFeignClient.deduct(commodityCode, count);
    }

    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void placePoint(String userId, String pointCode, Integer count) {
        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));
        Order order = new Order().setUserId(userId).setCommodityCode(pointCode).setCount(count).setMoney(orderMoney);
        orderMapper.insert(order);
        pointFeignClient.increase(pointCode, count);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(String userId, String commodityCode, Integer count) {
        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));

        Order order = new Order().setUserId(userId).setCommodityCode(commodityCode).setCount(count).setMoney(orderMoney);
        orderMapper.insert(order);

        accountFeignClient.reduce(userId, orderMoney);
    }
}
