package com.demo.kafka.todo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

@SpringBootApplication
public class Application {

	public static final String TODO_ACTIVITY_TOPIC = "todo-activity-topic";
	public static final String TODO_GROUP = "group_id";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public KafkaReceiver<String, String> fluxReceiver() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, TODO_GROUP);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		ReceiverOptions<String, String> receiverOptions = ReceiverOptions.create(props);

		ReceiverOptions<String, String> options = receiverOptions
				.subscription(Collections.singleton(TODO_ACTIVITY_TOPIC));

		return KafkaReceiver.create(options);

	}

}