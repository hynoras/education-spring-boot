package com.example.education_spring_boot.config;

import com.example.education_spring_boot.model.dto.student.detail.PersonalInfoForm;
import com.example.education_spring_boot.model.entity.Student;
import org.aspectj.weaver.tools.MatchingContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class ModelMapperConfig {
    private static final String UNKNOWN_VALUE = "Unknown";

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(PersonalInfoForm.class, Student.class).setPostConverter(ctx -> {
            Student studentCtx = ctx.getDestination();
            PersonalInfoForm form = ctx.getSource();
            studentCtx.setPermanentAddress(Optional.ofNullable(form.getPermanent_address()).orElse(UNKNOWN_VALUE));
            studentCtx.setEthnicGroup(Optional.ofNullable(form.getEthnic_group()).orElse(UNKNOWN_VALUE));
            studentCtx.setCitizenId(Optional.ofNullable(form.getCitizen_id()).orElse(UNKNOWN_VALUE));
            return studentCtx;
        }).addMappings(mapper -> {
            mapper.map(PersonalInfoForm::getFull_name, Student::setFullName);
            mapper.map(PersonalInfoForm::getDate_of_birth, Student::setBirthDate);
            mapper.map(PersonalInfoForm::getPermanent_address, Student::setPermanentAddress);
            mapper.map(PersonalInfoForm::getTemporary_address, Student::setTemporaryAddress);
            mapper.map(PersonalInfoForm::getEthnic_group, Student::setEthnicGroup);
            mapper.map(PersonalInfoForm::getCitizen_id, Student::setCitizenId);
        });
        return modelMapper;
    }
}
