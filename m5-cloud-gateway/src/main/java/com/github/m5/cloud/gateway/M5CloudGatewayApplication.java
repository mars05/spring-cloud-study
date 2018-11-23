package com.github.m5.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;

@SpringBootApplication
public class M5CloudGatewayApplication {
//    @Bean
//    public RouteLocator customRouteLocator(ThrottleWebFilterFactory throttle) {
//        return Route..locator()
//                .route("test")
//                .uri("http://httpbin.org:80")
//                .predicate(host("**.abc.org").and(path("/image/png")))
//                .addResponseHeader("X-TestHeader", "foobar")
//                .and()
//                .route("test2")
//                .uri("http://httpbin.org:80")
//                .predicate(path("/image/webp"))
//                .add(addResponseHeader("X-AnotherHeader", "baz"))
//                .and()
//                .build();
//    }

    public static void main(String[] args) {
        SpringApplication.run(M5CloudGatewayApplication.class, args);
    }
}
