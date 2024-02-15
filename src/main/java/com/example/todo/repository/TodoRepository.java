// Write your code here
// Write your code here
package com.example.todo.repository;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoH2Service;
import java.util.*;

public interface TodoRepository {
    ArrayList<Todo> getTodos();

    Todo addTodo(Todo todo);

    Todo getTodoById(int id);

    Todo updateTodo(Todo todo, int id);

    void deleteTodo(int id);

}