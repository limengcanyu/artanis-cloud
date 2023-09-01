package com.spring.cloud.point.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.cloud.point.service.entity.Point;
import com.spring.cloud.point.service.mapper.PointMapper;
import com.spring.cloud.point.service.service.PointService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rock
 * @since 2022-07-29
 */
@Service
public class PointServiceImpl extends ServiceImpl<PointMapper, Point> implements PointService {

    @Autowired
    private PointMapper pointMapper;

    @Override
    public void increase(String pointCode, int count) {
        if (pointCode.equals("point-2")) {
            throw new RuntimeException("异常: 模拟业务异常: point branch exception");
        }

        QueryWrapper<Point> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new Point().setPointCode(pointCode));
        Point point = pointMapper.selectOne(wrapper);
        point.setCount(point.getCount() + count);

        pointMapper.updateById(point);
    }

}
