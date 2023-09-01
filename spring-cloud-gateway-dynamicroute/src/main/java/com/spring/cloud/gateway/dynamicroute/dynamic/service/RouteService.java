package com.spring.cloud.gateway.dynamicroute.dynamic.service;

import com.alibaba.fastjson.JSONArray;

/**
 * <p>Description: Route Service </p>
 *
 * @author rock.jiang
 * Date 2019/12/24 14:53
 */
public interface RouteService {
    /**
     * 该处数据可从后台DB中获取
     *
     * @return
     */
    JSONArray getRouteDefinitions();
}
