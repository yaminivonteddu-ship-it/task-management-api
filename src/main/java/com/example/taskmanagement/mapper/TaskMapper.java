package com.example.taskmanagement.mapper;

import com.example.taskmanagement.dto.TaskCreateRequest;
import com.example.taskmanagement.dto.TaskResponse;
import com.example.taskmanagement.model.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

// This class converts between DTOs and Entity objects
@Component
public class TaskMapper {

    // Convert TaskCreateRequest to Task entity
    public Task toEntity(TaskCreateRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
        return task;
    }

    // Convert Task entity to TaskResponse
    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getCreatedAt(),
            task.getDueDate(),
            task.getIsCompleted()
        );
    }

    // Convert list of Task entities to list of TaskResponse
    public List<TaskResponse> toResponseList(List<Task> tasks) {
        return tasks.stream()
                   .map(this::toResponse)
                   .collect(Collectors.toList());
    }
}