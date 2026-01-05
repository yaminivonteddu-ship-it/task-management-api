package com.example.taskmanagement.service;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// This annotation tells Spring this is a service class (business logic)
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private NotificationService notificationService;

    // Get all tasks - cache the result for better performance
    @Cacheable(value = "tasks", key = "'all'")
    public List<Task> getAllTasks() {
        System.out.println("Fetching all tasks from database (not cached)");
        return taskRepository.findAll();
    }

    // Get task by ID - cache individual tasks
    @Cacheable(value = "tasks", key = "#id")
    public Optional<Task> getTaskById(Long id) {
        System.out.println("Fetching task " + id + " from database (not cached)");
        return taskRepository.findById(id);
    }

    // Create a new task
    public Task createTask(Task task) {
        // Set creation time automatically
        task.setCreatedAt(LocalDateTime.now());
        
        // Set default completion status if not provided
        if (task.getIsCompleted() == null) {
            task.setIsCompleted(false);
        }
        
        Task savedTask = taskRepository.save(task);
        
        // Send notification after task is created
        notificationService.sendTaskCreatedNotification(
            savedTask.getTitle(), 
            "user@example.com" // Mock email
        );
        
        return savedTask;
    }

    // Update an existing task
    public Optional<Task> updateTask(Long id, Task taskDetails) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        
        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            boolean wasCompleted = existingTask.getIsCompleted();
            
            // Update only the fields that are provided
            if (taskDetails.getTitle() != null) {
                existingTask.setTitle(taskDetails.getTitle());
            }
            if (taskDetails.getDescription() != null) {
                existingTask.setDescription(taskDetails.getDescription());
            }
            if (taskDetails.getDueDate() != null) {
                existingTask.setDueDate(taskDetails.getDueDate());
            }
            if (taskDetails.getIsCompleted() != null) {
                existingTask.setIsCompleted(taskDetails.getIsCompleted());
            }
            
            Task updatedTask = taskRepository.save(existingTask);
            
            // Send notification if task was just completed
            if (!wasCompleted && updatedTask.getIsCompleted()) {
                notificationService.sendTaskCompletedNotification(updatedTask.getTitle());
            }
            
            return Optional.of(updatedTask);
        }
        
        return Optional.empty();
    }

    // Delete a task
    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Get completed tasks
    public List<Task> getCompletedTasks() {
        return taskRepository.findByIsCompleted(true);
    }

    // Get pending tasks
    public List<Task> getPendingTasks() {
        return taskRepository.findByIsCompleted(false);
    }

    // Search tasks by title
    public List<Task> searchTasksByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }
}