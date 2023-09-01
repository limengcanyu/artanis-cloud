package com.spring.cloud.gateway.config;

import org.isomorphism.util.TokenBucket;
import org.isomorphism.util.TokenBuckets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * <p>Description: Throttle(节流阀) GatewayFilter</p>
 *
 * @author rock.jiang
 * Date 2019/12/24 12:30
 */
public class ThrottleGatewayFilter implements GatewayFilter {
    private static final Logger logger = LoggerFactory.getLogger(ThrottleGatewayFilter.class);

    /**
     * 令牌桶容量
     */
    int capacity;

    /**
     * 重新注满令牌
     */
    int refillTokens;

    /**
     * 重新注满周期
     */
    int refillPeriod;

    /**
     * 重新注满时间单位
     */
    TimeUnit refillUnit;

    public int getCapacity() {
        return capacity;
    }

    public ThrottleGatewayFilter setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public int getRefillTokens() {
        return refillTokens;
    }

    public ThrottleGatewayFilter setRefillTokens(int refillTokens) {
        this.refillTokens = refillTokens;
        return this;
    }

    public int getRefillPeriod() {
        return refillPeriod;
    }

    public ThrottleGatewayFilter setRefillPeriod(int refillPeriod) {
        this.refillPeriod = refillPeriod;
        return this;
    }

    public TimeUnit getRefillUnit() {
        return refillUnit;
    }

    public ThrottleGatewayFilter setRefillUnit(TimeUnit refillUnit) {
        this.refillUnit = refillUnit;
        return this;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 基于令牌桶算法限流
        TokenBucket tokenBucket = TokenBuckets.builder().withCapacity(capacity)
                .withFixedIntervalRefillStrategy(refillTokens, refillPeriod, refillUnit)
                .build();

        // TODO: get a token bucket for a key
        logger.debug("TokenBucket capacity: " + tokenBucket.getCapacity());

        // 尝试从令牌桶中获取令牌，获取成功后进行下一步，否则，结束请求，返回响应
        boolean consumed = tokenBucket.tryConsume();
        if (consumed) {
            return chain.filter(exchange);
        }
        exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        return exchange.getResponse().setComplete();
    }
}
