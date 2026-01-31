package com.example.todoapp.web;

import com.example.todoapp.domain.Todo;
import com.example.todoapp.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoApiController {
    private final TodoService todoService;

    public TodoApiController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> list() {
        return todoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> get(@PathVariable Long id) {
        Todo todo = todoService.findById(id);
        if (todo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(todo);
    }

    @PostMapping
    public ResponseEntity<Todo> create(@Valid @RequestBody TodoRequest request) {
        Todo created = todoService.create(request.getTitle());
        return ResponseEntity.created(URI.create("/api/todos/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> update(@PathVariable Long id, @Valid @RequestBody TodoRequest request) {
        Todo updated = todoService.update(id, request.getTitle(), request.getCompleted());
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!todoService.delete(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
