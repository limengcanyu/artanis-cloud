package com.spring.cloud.stock.service.mapper;

import com.spring.cloud.stock.service.entity.Stock;
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
public interface StockMapper extends BaseMapper<Stock> {

}
