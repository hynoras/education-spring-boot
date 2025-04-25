package com.example.education_spring_boot.config;

import com.example.education_spring_boot.model.dto.student.detail.PersonalInfoForm;
import com.example.education_spring_boot.model.entity.Student;
import com.example.education_spring_boot.utils.MapperUtils.StudentMappers;
import org.aspectj.weaver.tools.MatchingContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
