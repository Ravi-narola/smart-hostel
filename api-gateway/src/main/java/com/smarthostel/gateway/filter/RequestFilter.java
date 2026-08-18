package com.smarthostel.gateway.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class RequestFilter implements GlobalFilter, Ordered {

    private static final String REQUEST_ID = "X-Request-ID";

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

        String requestId =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst(REQUEST_ID);

        if (requestId == null || requestId.isBlank()) {
            requestId = UUID.randomUUID().toString();
        }

        ServerHttpRequest request =
                exchange.getRequest()
                        .mutate()
                        .header(REQUEST_ID, requestId)
                        .build();

        ServerWebExchange updatedExchange =
                exchange.mutate()
                        .request(request)
                        .build();

        updatedExchange.getResponse()
                .getHeaders()
                .add(REQUEST_ID, requestId);

        return chain.filter(updatedExchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}