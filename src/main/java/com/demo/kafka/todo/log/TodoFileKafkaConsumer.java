package com.demo.kafka.todo.log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.demo.kafka.todo.Application;
import com.demo.kafka.todo.bean.TodoActivity;

@Component
public class TodoFileKafkaConsumer {
	@KafkaListener(topics = Application.TODO_ACTIVITY_TOPIC, groupId = Application.TODO_GROUP)
	private void consume(TodoActivity activity) throws IOException {
		File file = new File("todo-activity-log.txt");
		file.createNewFile();
		String line = activity.toCSV() + System.getProperty("line.separator");
		Files.write(Paths.get(file.toURI()), line.getBytes(), StandardOpenOption.APPEND);
	}
}
