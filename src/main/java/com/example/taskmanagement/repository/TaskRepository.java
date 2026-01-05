package com.example.taskmanagement.repository;

import com.example.taskmanagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// This interface gives us basic database operations for free
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    // Spring Data JPA will automatically create this method for us
    // It finds all tasks where isCompleted equals the given value
    List<Task> findByIsCompleted(Boolean isCompleted);
    
    // Find tasks by title containing a string (case-insensitive search)
    List<Task> findByTitleContainingIgnoreCase(String title);
}