package com.demo.kafka.example;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleKafkaProducer {

	private final KafkaProducer<String, String> producer;
	private final String topic;

	public SimpleKafkaProducer(Properties config, String topic) {
		this.producer = new KafkaProducer<String, String>(config);
		this.topic = topic;
	}

	public void produce(String message) {
		ProducerRecord<String, String> pr = new ProducerRecord<String, String>(topic, message);
		producer.send(pr);
		producer.close();
	}

}
