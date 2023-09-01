package com.spring.cloud.gateway.dynamicroute.dynamic.repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spring.cloud.gateway.dynamicroute.dynamic.service.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;

import static java.util.Collections.synchronizedMap;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2019/12/24 14:32
 */
public class MysqlRouteDefinitionRepository implements RouteDefinitionRepository {
    private static final Logger logger = LoggerFactory.getLogger(MysqlRouteDefinitionRepository.class);

    @Autowired
    private RouteService routeService;

    private final Map<String, RouteDefinition> routes = synchronizedMap(new LinkedHashMap<>());

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        logger.debug("save ====================================");
        return route.flatMap(r -> {
            logger.debug("r ===: {}", r);
            routes.put(r.getId(), r);
            logger.debug("routes ===: {}", JSONObject.toJSONString(routes));
            return Mono.empty();
        });
    }

    public void save(RouteDefinition route) {
        logger.debug("save ====================================");

        routes.put(route.getId(), route);
    }


    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            if (routes.containsKey(id)) {
                routes.remove(id);
                return Mono.empty();
            }
            return Mono.defer(() -> Mono.error(
                    new NotFoundException("RouteDefinition not found: " + routeId)));
        });
    }

    public void delete(String routeId) {
        routes.remove(routeId);
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        JSONArray routeJsonArray = routeService.getRouteDefinitions();
        if (!CollectionUtils.isEmpty(routeJsonArray)) {
            int size = routeJsonArray.size();
            for (int i = 0; i < size; i++) {
                JSONObject routeJsonObject = routeJsonArray.getJSONObject(i);

                RouteDefinition routeDefinition = new RouteDefinition();
                routeDefinition.setId(routeJsonObject.getString("id"));

                List<PredicateDefinition> predicates = new ArrayList<>();
                PredicateDefinition predicateDefinition = new PredicateDefinition(routeJsonObject.getString("path"));
                predicates.add(predicateDefinition);
                routeDefinition.setPredicates(predicates);

                List<FilterDefinition> filters = new ArrayList<>();
                FilterDefinition filterDefinition = new FilterDefinition("StripPrefix=1");
                filters.add(filterDefinition);
                routeDefinition.setFilters(filters);

                routeDefinition.setUri(URI.create(routeJsonObject.getString("uri")));

                Map<String, Object> metadata = new HashMap<>();
                metadata.put("response-timeout", 200);
                metadata.put("connect-timeout", 200);
                routeDefinition.setMetadata(metadata);

                routes.put(routeJsonObject.getString("id"), routeDefinition);
            }
        }


        return Flux.fromIterable(routes.values());
    }

}
