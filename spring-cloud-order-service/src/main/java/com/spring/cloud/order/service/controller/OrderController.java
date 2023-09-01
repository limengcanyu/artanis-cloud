package com.spring.cloud.order.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.spring.cloud.order.service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * http://localhost:8082/helloOrder
     *
     * @return
     */
    @RequestMapping("/helloOrder")
    public String helloOrder() {
        log.debug("call helloOrder ================");
        return "hello order";
    }

    /**
     * http://localhost:8082/getUserOrders
     *
     * @return
     */
    @RequestMapping("/getUserOrders")
    public String getUserOrders(@RequestBody Map<String, Object> map) {
        log.debug("call getUserOrders ================ param map: {}", JSONObject.toJSONString(map));

        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "user orders";
    }

    /**
     * 下单：插入订单表、扣减库存，模拟回滚
     *
     * @return
     */
    @RequestMapping("/placeOrder/commit")
    public Boolean placeOrderCommit() {
        orderService.placeOrder("1", "product-1", 1);
        return true;
    }

    /**
     * 下单：插入订单表、扣减库存，模拟回滚
     *
     * @return
     */
    @RequestMapping("/placeOrder/rollback")
    public Boolean placeOrderRollback() {
        // product-2 扣库存时模拟了一个业务异常,
        orderService.placeOrder("1", "product-2", 1);
        return true;
    }

    @RequestMapping("/placeOrder")
    public Boolean placeOrder(String userId, String commodityCode, Integer count) {
        orderService.placeOrder(userId, commodityCode, count);
        return true;
    }

    /**
     * 下单：插入订单表、增积分，模拟回滚
     *
     * @return
     */
    @RequestMapping("/placePoint/commit")
    public Boolean placePointCommit() {
        orderService.placePoint("1", "point-1", 1);
        return true;
    }

    /**
     * 下单：插入订单表、增积分，模拟回滚
     *
     * @return
     */
    @RequestMapping("/placePoint/rollback")
    public Boolean placePointRollback() {
        // point-2 扣库存时模拟了一个业务异常,
        orderService.placePoint("1", "point-2", 1);
        return true;
    }

}
