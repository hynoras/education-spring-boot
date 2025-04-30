package com.example.education_spring_boot.shared.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Apply CORS policy to all endpoints
                .allowedOrigins("http://localhost:3000","https://education-reactjs-plum.vercel.app") // Allowed origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // HTTP methods
                .allowedHeaders("Authorization", "Content-Type") // Allowed headers
                .allowCredentials(true); // Allow credentials (cookies, Authorization headers, etc.)
    }
}
