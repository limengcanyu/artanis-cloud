package com.spring.cloud.gateway.dynamicroute.dynamic.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spring.cloud.gateway.dynamicroute.dynamic.service.RouteService;
import org.springframework.stereotype.Service;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2019/12/24 14:54
 */
@Service
public class RouteServiceImpl implements RouteService {
    JSONArray routes = new JSONArray();

    @Override
    public JSONArray getRouteDefinitions() {

        JSONObject route = new JSONObject();
        route.put("id", "security_route");
        route.put("uri", "lb://spring-cloud-security");
        route.put("path", "Path=/security/**");
        routes.add(route);

        route = new JSONObject();
        route.put("id", "microservice1_route");
        route.put("uri", "lb://spring-cloud-microservice1");
        route.put("path", "Path=/microservice1/**");
        routes.add(route);

        return routes;
    }
}
