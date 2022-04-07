package com.jx.springCloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LDW
 * @date 2022/4/5 16:05
 */
@Configuration
public class GateWayConfig {
    //自定义路由
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_znp",
                predicateSpec -> predicateSpec.path("/guonei")
                        .uri("http://news.baidu.com/guonei")
        ).build();
        return routes.build();
    }
}
