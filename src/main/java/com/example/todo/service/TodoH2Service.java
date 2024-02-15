/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */

// Write your code here
/*
 * You can use the following import statements
 * 
 * import org.springframework.web.server.ResponseStatusException;
 * import org.springframework.http.HttpStatus;
 * 
 */

package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.example.todo.model.Todo;
import com.example.todo.model.TodoRowMapper;
import com.example.todo.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

// Do not modify the below code
@Service
public class TodoH2Service implements TodoRepository {
    @Autowired
    public JdbcTemplate db;

    public ArrayList<Todo> getTodos() {
        Collection<Todo> todoCollection = db.query("select * from todoList", new TodoRowMapper());
        ArrayList<Todo> todos = new ArrayList<>(todoCollection);
        return todos;

    }

    public Todo addTodo(Todo todo) {
        db.update("insert into todoList (todo,status,priority) values(?, ?, ?)", todo.getTodo(), todo.getStatus(),
                todo.getPriority());
        Todo savedTodo = db.queryForObject("select * from todoList where todo =? and status =? and priority=? ",
                new TodoRowMapper(), todo.getTodo(), todo.getStatus(), todo.getPriority());
        return savedTodo;

    }

    public Todo getTodoById(int id) {
        try {
            Todo todo = db.queryForObject("select * from todoList where id =?  ", new TodoRowMapper(), id);
            return todo;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    public Todo updateTodo(Todo todo, int id) {

        if (todo.getTodo() != null) {
            db.update("update todoList set todo=? where id=?", todo.getTodo(), id);

        }
        if (todo.getPriority() != null) {

            db.update("update todoList set priority=? where id=?", todo.getPriority(), id);
        }
        if (todo.getStatus() != null) {

            db.update("update todoList set status=? where id=?", todo.getStatus(), id);
        }

        return getTodoById(id);
    }

    public void deleteTodo(int id) {
        db.update("delete from todoList where id=?", id);

    }
}
