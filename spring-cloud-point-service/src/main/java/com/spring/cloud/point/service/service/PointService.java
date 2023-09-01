package com.spring.cloud.point.service.service;

import com.spring.cloud.point.service.entity.Point;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rock
 * @since 2022-07-29
 */
public interface PointService extends IService<Point> {

    void increase(String pointCode, int count);

}
