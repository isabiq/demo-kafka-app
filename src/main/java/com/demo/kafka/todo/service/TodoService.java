package com.demo.kafka.todo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.demo.kafka.todo.bean.Todo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TodoService {
	private static Map<Integer, Todo> todos = new HashMap<>();
	private static int todoCount = 3;

	static {
		todos.put(1, new Todo(1, "To Do 1", "To Do Description 1", true));
		todos.put(2, new Todo(2, "To Do 2", "To Do Description 2", false));
		todos.put(3, new Todo(3, "To Do 3", "To Do Description 3", false));
	}

	public Mono<Todo> retrieveTodo(int id) {
		return Mono.just(todos.get(id));
	}

	public Flux<Todo> retrieveTodos() {
		return Flux.fromIterable(todos.values());
	}

	public void addTodo(String title, String desc, boolean isDone) {
		Todo todo = new Todo(++todoCount, title, desc, isDone);
		todos.put(todoCount, todo);
	}

}