package com.example.todoapp.web;

import com.example.todoapp.domain.Todo;
import com.example.todoapp.service.TodoService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Validated
public class TodoUiController {
    private final TodoService todoService;

    public TodoUiController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Todo> todos = todoService.findAll();
        model.addAttribute("todos", todos);
        return "index";
    }

    @PostMapping("/todos")
    public String create(@RequestParam("title") @NotBlank String title, RedirectAttributes redirectAttributes) {
        todoService.create(title);
        redirectAttributes.addFlashAttribute("message", "To Do を追加しました");
        return "redirect:/";
    }

    @PostMapping("/todos/{id}/toggle")
    public String toggle(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Todo updated = todoService.toggle(id);
        if (updated == null) {
            redirectAttributes.addFlashAttribute("message", "対象が見つかりませんでした");
        }
        return "redirect:/";
    }

    @PostMapping("/todos/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (!todoService.delete(id)) {
            redirectAttributes.addFlashAttribute("message", "対象が見つかりませんでした");
        }
        return "redirect:/";
    }
}
