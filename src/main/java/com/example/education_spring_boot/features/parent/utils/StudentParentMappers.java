package com.example.education_spring_boot.features.parent.utils;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import com.example.education_spring_boot.features.parent.models.dtos.ParentInfoForm;
import com.example.education_spring_boot.features.parent.models.entities.StudentParent;
import com.example.education_spring_boot.shared.constants.generic.GenericValues;

import jakarta.annotation.PostConstruct;

@Configuration
public class StudentParentMappers {
  private final ModelMapper modelMapper;

  public StudentParentMappers(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @PostConstruct
  public void init() {
    parentInfoMapper();
  }

  public void parentInfoMapper() {
    modelMapper
        .typeMap(ParentInfoForm.class, StudentParent.class)
        .setPostConverter(
            ctx -> {
              StudentParent parentCtx = ctx.getDestination();
              ParentInfoForm form = ctx.getSource();
              parentCtx.setPermanentAddress(
                  Optional.ofNullable(form.getPermanent_address()).orElse(GenericValues.UNKNOWN));
              parentCtx.setNationality(
                  Optional.ofNullable(form.getNationality()).orElse(GenericValues.UNKNOWN));
              return parentCtx;
            })
        .addMappings(
            mapper -> {
              mapper.map(ParentInfoForm::getStudent_id, StudentParent::setStudentId);
              mapper.map(ParentInfoForm::getFull_name, StudentParent::setFullName);
              mapper.map(ParentInfoForm::getBirth_date, StudentParent::setBirthDate);
              mapper.map(ParentInfoForm::getPermanent_address, StudentParent::setPermanentAddress);
            });
  }
}
