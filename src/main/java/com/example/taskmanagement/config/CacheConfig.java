package com.example.taskmanagement.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

// Configuration class for caching setup
@Configuration
public class CacheConfig {

    // Configure Caffeine cache manager with custom settings
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        
        // Configure cache properties
        cacheManager.setCaffeine(Caffeine.newBuilder()
                // Maximum number of entries in cache
                .maximumSize(1000)
                // Cache entries expire after 10 minutes of being written
                .expireAfterWrite(10, TimeUnit.MINUTES)
                // Cache entries expire after 5 minutes of last access
                .expireAfterAccess(5, TimeUnit.MINUTES)
                // Enable cache statistics for monitoring
                .recordStats());
        
        return cacheManager;
    }
}