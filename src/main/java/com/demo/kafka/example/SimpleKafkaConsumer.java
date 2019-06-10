package com.demo.kafka.example;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class SimpleKafkaConsumer {
	private final KafkaConsumer<String, String> consumer; 
	private final String topic;

	public SimpleKafkaConsumer(Properties config, String topic) {
		this.consumer = new KafkaConsumer<String, String>(config);
		this.topic = topic;
		this.consumer.subscribe(Arrays.asList(this.topic));
	}

	public ConsumerRecords<String, String> consume() {
		ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
		return records;
	}
}
