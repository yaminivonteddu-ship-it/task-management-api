package com.example.taskmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

// This annotation tells JPA this is a database table
@Entity
@Table(name = "tasks")
public class Task {
    
    // Primary key that auto-increments
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Task title - cannot be null or empty, max 100 characters
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    @Column(nullable = false)
    private String title;
    
    // Task description - optional but max 1000 characters if provided
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    @Column(length = 1000)
    private String description;
    
    // When the task was created
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // When the task is due - must be in the future
    @Future(message = "Due date must be in the future")
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    
    // Whether the task is completed
    @Column(name = "is_completed")
    private Boolean isCompleted = false;

    // Default constructor (required by JPA)
    public Task() {}

    // Constructor with required fields
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters (these allow us to access and modify the fields)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}