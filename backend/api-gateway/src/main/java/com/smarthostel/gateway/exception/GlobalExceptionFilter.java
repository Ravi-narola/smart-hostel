package com.smarthostel.gateway.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class GlobalExceptionFilter implements GlobalFilter, Ordered {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {

        return chain.filter(exchange)
                .onErrorResume(exception ->
                        handleException(exchange, exception));
    }

    private Mono<Void> handleException(
            ServerWebExchange exchange,
            Throwable exception) {

        exchange.getResponse()
                .setStatusCode(HttpStatus.BAD_GATEWAY);

        exchange.getResponse()
                .getHeaders()
                .setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", HttpStatus.BAD_GATEWAY.value());
        response.put("error", "Bad Gateway");
        response.put(
                "message",
                "Unable to communicate with the requested service"
        );
        response.put(
                "path",
                exchange.getRequest()
                        .getURI()
                        .getPath()
        );

        try {

            byte[] bytes = objectMapper.writeValueAsBytes(response);

            return exchange.getResponse()
                    .writeWith(
                            Mono.just(
                                    exchange.getResponse()
                                            .bufferFactory()
                                            .wrap(bytes)
                            )
                    );

        } catch (Exception e) {

            return exchange.getResponse()
                    .setComplete();
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }
}