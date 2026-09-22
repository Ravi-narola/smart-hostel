package com.smarthostel.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(
            org.springframework.web.server.ServerWebExchange exchange,
            org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

        long startTime = System.currentTimeMillis();

        String method = exchange.getRequest()
                .getMethod()
                .name();

        String path = exchange.getRequest()
                .getURI()
                .getPath();

        log.info("Gateway Request: {} {}", method, path);

        return chain.filter(exchange)
                .doFinally(signal -> {

                    long duration =
                            System.currentTimeMillis() - startTime;

                    int statusCode = exchange.getResponse()
                            .getStatusCode() != null
                            ? exchange.getResponse()
                                .getStatusCode()
                                .value()
                            : 0;

                    log.info(
                            "Gateway Response: {} {} -> {} ({} ms)",
                            method,
                            path,
                            statusCode,
                            duration
                    );
                });
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}