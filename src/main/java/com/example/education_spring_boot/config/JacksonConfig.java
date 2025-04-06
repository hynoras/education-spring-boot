package com.example.education_spring_boot.config;

import com.example.education_spring_boot.model.entity.Major;
import com.example.education_spring_boot.util.JSONUtil.StudentDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public SimpleModule studentDeserializerModule(StudentDeserializer studentDeserializer) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Major.class, studentDeserializer);
        return module;
    }
}

