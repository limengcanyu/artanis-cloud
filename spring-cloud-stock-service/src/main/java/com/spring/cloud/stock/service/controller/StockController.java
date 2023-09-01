package com.spring.cloud.stock.service.controller;

import com.spring.cloud.stock.service.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rock
 * @since 2022-07-29
 */
@Slf4j
@RestController
@RequestMapping("stock")
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * 减库存
     *
     * @param commodityCode 商品代码
     * @param count         数量
     * @return
     */
    @GetMapping(path = "/deduct")
    public Boolean deduct(String commodityCode, Integer count) {
        log.debug("StockController deduct ......");
        stockService.deduct(commodityCode, count);
        return true;
    }

}
