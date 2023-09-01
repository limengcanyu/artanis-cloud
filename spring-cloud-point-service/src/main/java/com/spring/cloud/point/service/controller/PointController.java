package com.spring.cloud.point.service.controller;

import com.spring.cloud.point.service.service.PointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rock
 * @since 2022-07-29
 */
@Slf4j
@RestController
@RequestMapping("/point")
public class PointController {

    @Autowired
    private PointService pointService;

    /**
     * 增积分
     *
     * @param pointCode     积分代码
     * @param count         数量
     * @return
     */
    @GetMapping(path = "/increase")
    public Boolean increase(String pointCode, Integer count) {
        log.debug("PointController deduct pointCode: {} count: {} ......", pointCode, count);
        pointService.increase(pointCode, count);
        return true;
    }

}
