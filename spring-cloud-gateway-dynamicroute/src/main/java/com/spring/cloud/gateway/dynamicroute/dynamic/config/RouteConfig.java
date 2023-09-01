package com.spring.cloud.gateway.dynamicroute.dynamic.config;

import com.spring.cloud.gateway.dynamicroute.dynamic.repository.MysqlRouteDefinitionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: Route Config </p>
 *
 * @author rock.jiang
 * Date 2019/12/23 17:55
 */
@Configuration
public class RouteConfig {

    /**
     * 动态路由获取Bean定义
     *
     * @return
     */
    @Bean
    public MysqlRouteDefinitionRepository mysqlRouteDefinitionRepository() {
        return new MysqlRouteDefinitionRepository();
    }
}
