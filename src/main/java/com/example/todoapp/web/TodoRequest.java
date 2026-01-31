package com.example.todoapp.web;

import jakarta.validation.constraints.NotBlank;

public class TodoRequest {
    @NotBlank
    private String title;
    private Boolean completed;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
