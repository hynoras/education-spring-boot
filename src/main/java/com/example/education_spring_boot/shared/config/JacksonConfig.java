package com.example.education_spring_boot.shared.config;

import com.example.education_spring_boot.features.location.models.entities.Location;
import com.example.education_spring_boot.features.major.models.entities.Major;
import com.example.education_spring_boot.shared.utils.JSONUtils.LocationDeserializer;
import com.example.education_spring_boot.features.major.utils.MajorDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public SimpleModule majorDeserializerModule(MajorDeserializer majorDeserializer) {
        SimpleModule majorModule = new SimpleModule();
        majorModule.addDeserializer(Major.class, majorDeserializer);
        return majorModule;
    }

    @Bean
    public SimpleModule locationDeserializerModule(LocationDeserializer locationDeserializer) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Location.class, locationDeserializer);
        return module;
    }
}

