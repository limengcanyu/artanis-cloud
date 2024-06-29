package com.spring.cloud.common.circuitbreaker.resilience4j;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.ThreadPoolBulkhead;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import io.github.resilience4j.retry.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.ServerResponse.permanentRedirect;

@Slf4j
@SpringBootApplication
public class SpringCloudCommonCircuitbreakerResilience4jApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringCloudCommonCircuitbreakerResilience4jApplication.class, args);
		System.out.println(context.getBean(CircuitBreakerRegistry.class));

	}

//
//	@Bean
//	RouterFunction<ServerResponse> redirectRoot() {
//		return route(GET("/"),
//				req -> permanentRedirect(URI.create("/actuator")).build());
//	}
//
//	@Bean
//	public CircuitBreakerConfigCustomizer testCustomizer() {
//		return CircuitBreakerConfigCustomizer
//				.of("backendA", builder -> builder.slidingWindowSize(100));
//	}
//
//	@Bean
//	public RegistryEventConsumer<CircuitBreaker> myRegistryEventConsumer() {
//
//		return new RegistryEventConsumer<CircuitBreaker>() {
//			@Override
//			public void onEntryAddedEvent(EntryAddedEvent<CircuitBreaker> entryAddedEvent) {
//				entryAddedEvent.getAddedEntry().getEventPublisher().onEvent(event -> log.info(event.toString()));
//			}
//
//			@Override
//			public void onEntryRemovedEvent(EntryRemovedEvent<CircuitBreaker> entryRemoveEvent) {
//
//			}
//
//			@Override
//			public void onEntryReplacedEvent(EntryReplacedEvent<CircuitBreaker> entryReplacedEvent) {
//
//			}
//		};
//	}
//
//	@Bean
//	public RegistryEventConsumer<Retry> myRetryRegistryEventConsumer() {
//
//		return new RegistryEventConsumer<Retry>() {
//			@Override
//			public void onEntryAddedEvent(EntryAddedEvent<Retry> entryAddedEvent) {
//				entryAddedEvent.getAddedEntry().getEventPublisher().onEvent(event -> log.info(event.toString()));
//			}
//
//			@Override
//			public void onEntryRemovedEvent(EntryRemovedEvent<Retry> entryRemoveEvent) {
//
//			}
//
//			@Override
//			public void onEntryReplacedEvent(EntryReplacedEvent<Retry> entryReplacedEvent) {
//
//			}
//		};
//	}

}
