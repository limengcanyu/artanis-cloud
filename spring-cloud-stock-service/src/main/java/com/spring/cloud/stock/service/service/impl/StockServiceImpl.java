package com.spring.cloud.stock.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.cloud.stock.service.entity.Stock;
import com.spring.cloud.stock.service.mapper.StockMapper;
import com.spring.cloud.stock.service.service.StockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rock
 * @since 2022-07-29
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deduct(String commodityCode, int count) {
        if (commodityCode.equals("product-2")) {
            throw new RuntimeException("异常: 模拟业务异常: stock branch exception");
        }

        QueryWrapper<Stock> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new Stock().setCommodityCode(commodityCode));
        Stock stock = stockMapper.selectOne(wrapper);
        stock.setCount(stock.getCount() - count);

        stockMapper.updateById(stock);
    }
}
