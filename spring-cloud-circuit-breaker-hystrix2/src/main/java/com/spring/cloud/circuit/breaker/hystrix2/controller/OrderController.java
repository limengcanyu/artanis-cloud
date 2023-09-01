package com.spring.cloud.circuit.breaker.hystrix2.controller;

import com.spring.cloud.circuit.breaker.hystrix2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: Store Controller </p>
 *
 * @author rock.jiang
 * Date 2020/04/19 19:43
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * localhost:8300/getOrders
     *
     * @return
     */
    @RequestMapping("/getOrders")
    public String getOrders() {
        try {
            return orderService.getOrders(null);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * localhost:8300/getStores
     *
     * @return
     */
    @RequestMapping("/getStores")
    public String getStores() {
        return orderService.getStores();
    }
}
