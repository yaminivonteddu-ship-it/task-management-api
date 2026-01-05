package com.example.taskmanagement.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

// This service demonstrates REST client calls to external APIs
// NOTE: This is a DEMO service - no real emails are sent!
// It calls JSONPlaceholder (a fake API) to show how external API integration works
@Service
public class NotificationService {

    private final WebClient webClient;

    // JSONPlaceholder is a fake REST API for testing (https://jsonplaceholder.typicode.com)
    @Value("${notification.service.url:https://jsonplaceholder.typicode.com}")
    private String notificationServiceUrl;

    public NotificationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(notificationServiceUrl).build();
    }

    // DEMO: Simulate calling an external notification service (no real email sent)
    public void sendTaskCreatedNotification(String taskTitle, String userEmail) {
        // Create a fake notification payload (this goes to JSONPlaceholder, not real email)
        Map<String, Object> notification = new HashMap<>();
        notification.put("title", "New Task Created");
        notification.put("body", "Task '" + taskTitle + "' has been created");
        notification.put("userId", 1); // Mock user ID
        
        // Make async HTTP call to JSONPlaceholder (fake API for demonstration)
        webClient.post()
                .uri("/posts") // JSONPlaceholder endpoint - returns fake response
                .bodyValue(notification)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> 
                    System.out.println("DEMO: External API call successful: " + response))
                .doOnError(error -> 
                    System.err.println("DEMO: External API call failed: " + error.getMessage()))
                .subscribe(); // Fire and forget - don't wait for response
    }

    // DEMO: Simulate task completion notification (no real email sent)
    public void sendTaskCompletedNotification(String taskTitle) {
        Map<String, Object> notification = new HashMap<>();
        notification.put("title", "Task Completed");
        notification.put("body", "Task '" + taskTitle + "' has been completed");
        notification.put("userId", 1);
        
        webClient.post()
                .uri("/posts")
                .bodyValue(notification)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> 
                    System.out.println("Completion notification sent: " + response))
                .doOnError(error -> 
                    System.err.println("Failed to send completion notification: " + error.getMessage()))
                .subscribe();
    }

    // Synchronous call example - get user info from external service
    public String getUserInfo(Long userId) {
        try {
            return webClient.get()
                    .uri("/users/{id}", userId)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block(); // This blocks and waits for response
        } catch (Exception e) {
            System.err.println("Failed to get user info: " + e.getMessage());
            return "Unknown User";
        }
    }
}