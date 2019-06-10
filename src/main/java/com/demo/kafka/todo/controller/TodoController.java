package com.demo.kafka.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.kafka.todo.bean.Todo;
import com.demo.kafka.todo.service.TodoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TodoController {

	@Autowired
	private TodoService todoService;

	@GetMapping(path = "/hello")
	public String gretting() {
		return "Welcome To Do";
	}

	@GetMapping("/todos")
	public Flux<Todo> retrieveTodos() {
		return todoService.retrieveTodos();
	}

	@GetMapping(path = "/todos/{id}")
	public Mono<Todo> retrieveTodo(@PathVariable int id) {
		Mono<Todo> todo = todoService.retrieveTodo(id);
		if (todo == null) {
			throw new RuntimeException("Todo Not Found");
		}
		return todo;
	}

	@PostMapping("/todos")
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody Todo todo) {
		todoService.addTodo(todo.getTitle(), todo.getDesc(), todo.isDone());
	}
}