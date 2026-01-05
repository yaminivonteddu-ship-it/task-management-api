package com.example.taskmanagement.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

// This class defines what data is required to create a task
public class TaskCreateRequest {
    
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;
    
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;
    
    @Future(message = "Due date must be in the future")
    private LocalDateTime dueDate;

    // Default constructor
    public TaskCreateRequest() {}

    // Constructor with required fields
    public TaskCreateRequest(String title, String description, LocalDateTime dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}