package com.example.education_spring_boot.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.education_spring_boot.features.auth.models.entities.Account;
import com.example.education_spring_boot.features.auth.utils.AccountDeserializer;
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

  @Bean
  public SimpleModule accountDeserializerModule(AccountDeserializer accountDeserializer) {
    SimpleModule accountModule = new SimpleModule();
    accountModule.addDeserializer(Account.class, accountDeserializer);
    return accountModule;
  }
}
