package com.example.todoapp.service;

import com.example.todoapp.domain.Todo;
import com.example.todoapp.mapper.TodoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoMapper todoMapper;

    public TodoService(TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }

    public List<Todo> findAll() {
        return todoMapper.findAll();
    }

    public Todo findById(Long id) {
        return todoMapper.findById(id);
    }

    public Todo create(String title) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setCompleted(false);
        todoMapper.insert(todo);
        return todoMapper.findById(todo.getId());
    }

    public Todo update(Long id, String title, Boolean completed) {
        Todo todo = todoMapper.findById(id);
        if (todo == null) {
            return null;
        }
        todo.setTitle(title);
        todo.setCompleted(completed != null ? completed : todo.getCompleted());
        todoMapper.update(todo);
        return todoMapper.findById(id);
    }

    public boolean delete(Long id) {
        return todoMapper.deleteById(id) > 0;
    }

    public Todo toggle(Long id) {
        Todo todo = todoMapper.findById(id);
        if (todo == null) {
            return null;
        }
        todo.setCompleted(!Boolean.TRUE.equals(todo.getCompleted()));
        todoMapper.update(todo);
        return todoMapper.findById(id);
    }
}
