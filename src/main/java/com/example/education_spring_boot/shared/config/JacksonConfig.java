package com.example.education_spring_boot.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.education_spring_boot.features.major.models.entities.Major;
import com.example.education_spring_boot.features.major.utils.MajorDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class JacksonConfig {

  @Bean
  public SimpleModule majorDeserializerModule(MajorDeserializer majorDeserializer) {
    SimpleModule majorModule = new SimpleModule();
    majorModule.addDeserializer(Major.class, majorDeserializer);
    return majorModule;
  }
}
