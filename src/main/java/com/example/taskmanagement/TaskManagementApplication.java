package com.example.taskmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

// This annotation tells Spring Boot this is the main application class
@SpringBootApplication
@EnableCaching  // Enable caching support
public class TaskManagementApplication {

    public static void main(String[] args) {
        // This starts the Spring Boot application
        SpringApplication.run(TaskManagementApplication.class, args);
    }
}