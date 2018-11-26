package com.github.m5.cloud.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

@Slf4j
@Configuration
@SpringBootApplication
public class M5CloudGatewayApplication {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("m5-config-1", predicateSpec -> predicateSpec.host("www.m5.com")
                        .and().weight("m5-config", 1)
                        .uri("http://127.0.0.1:9090"))
                .route("m5-config-2", predicateSpec -> predicateSpec.host("www.m5.com")
                        .and().weight("m5-config", 1)
                        .uri("http://www.ituring.com.cn"))
                .build();
    }

//    @Bean
//    public LoadBalancerClientFilter loadBalancerClientFilter(LoadBalancerClient client) {
//        return new LoadBalancerClientFilter(client) {
//            @Override
//            protected ServiceInstance choose(ServerWebExchange exchange) {
//                System.out.println("选择");
//                return super.choose(exchange);
//            }
//        };
//    }

    @Bean
    public GlobalFilter globalFilter() {
        return (exchange, chain) -> {
            log.info("first pre filter");
            log.info("{}", exchange.getRequest().getURI());

            Object attribute = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
            log.info("{}", attribute);
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("third post filter");
            }));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(M5CloudGatewayApplication.class, args);
    }
}
