package com.spring.cloud.stock.service.service;

import com.spring.cloud.stock.service.entity.Stock;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rock
 * @since 2022-07-29
 */
public interface StockService extends IService<Stock> {

    void deduct(String commodityCode, int count);

}
