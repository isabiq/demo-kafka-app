package com.demo.kafka.example;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);
	private static final String KAFKA_SERVERS = "localhost:9092";
	private static final String GROUP_ID = "test-group";
	private static final String TOPIC = "my-test-topic";

	public static void main(String[] args) {
		
		String message = "simple message to kafka";
		new SimpleKafkaProducer(createProducerConfig(), TOPIC).produce(message);
		
		SimpleKafkaConsumer simpleKafkaConsumer = new SimpleKafkaConsumer(createConsumerConfig(), TOPIC);
		while (true) {
			ConsumerRecords<String, String> records = simpleKafkaConsumer.consume();
			for (ConsumerRecord<String, String> consumerRecord : records) {
				LOGGER.info("Consumed Value : " + consumerRecord.value());
			}
		}
	}

	private static Properties createProducerConfig() {
		Properties props = new Properties();
		props.put("bootstrap.servers", KAFKA_SERVERS);
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 1000);
		props.put("linger.ms", 1);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		return props;
	}

	private static Properties createConsumerConfig() {
		Properties props = new Properties();
		props.put("bootstrap.servers", KAFKA_SERVERS);
		props.put("group.id", GROUP_ID);
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("auto.offset.reset", "earliest");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		return props;
	}

}
