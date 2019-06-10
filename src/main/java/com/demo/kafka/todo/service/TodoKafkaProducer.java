package com.demo.kafka.todo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.demo.kafka.todo.Application;
import com.demo.kafka.todo.bean.TodoActivity;
import com.demo.kafka.todo.filters.KafkaLogFilter;

@Service
public class TodoKafkaProducer {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaLogFilter.class);
	@Autowired
	KafkaTemplate<String, TodoActivity> kafkaTemplate;

	public void produce(TodoActivity activity) {
		LOGGER.info("send activity to kafka");
		this.kafkaTemplate.send(Application.TODO_ACTIVITY_TOPIC, activity);
	}
}
