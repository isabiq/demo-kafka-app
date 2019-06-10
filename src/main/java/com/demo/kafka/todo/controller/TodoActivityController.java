package com.demo.kafka.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;

@RestController
public class TodoActivityController {

	@Autowired
	private KafkaReceiver<String, String> kafkaReceiver;

	@GetMapping(value = "/todo-feed", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> getTodoEvents() {
		return kafkaReceiver.receive().map(ReceiverRecord::value);
	}
}
