package com.iso.todos.service;

import com.iso.todos.request.TodoRequest;
import com.iso.todos.response.TodoResponse;

import java.util.List;


public interface TodoService {
    List<TodoResponse> getAllTodos();
    TodoResponse createTodo(TodoRequest todoRequest);
    TodoResponse toggleTodoCompletion(long id);
    void deleteTodo(long id);
}
