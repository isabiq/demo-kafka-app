package com.demo.kafka.todo.filters;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.demo.kafka.todo.bean.TodoActivity;
import com.demo.kafka.todo.service.TodoKafkaProducer;

import reactor.core.publisher.Mono;

@Component
public class KafkaLogFilter implements WebFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaLogFilter.class);

	@Autowired
	private TodoKafkaProducer todoKafkaProducer;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		String path = exchange.getRequest().getURI().getPath();
		LOGGER.info("Serving '{}'", path);

		return chain.filter(exchange).doAfterTerminate(() -> {

			todoKafkaProducer.produce(new TodoActivity(path, exchange.getRequest().getMethod().toString(),
					exchange.getResponse().getStatusCode().toString(), new Date()));
		});
	}
}