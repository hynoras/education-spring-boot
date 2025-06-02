package com.example.education_spring_boot.features.student.utils;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import com.example.education_spring_boot.features.student.models.dtos.detail.PersonalInfoForm;
import com.example.education_spring_boot.features.student.models.dtos.list.StudentList;
import com.example.education_spring_boot.features.student.models.entities.Student;
import com.example.education_spring_boot.shared.constants.generic.GenericValues;

import jakarta.annotation.PostConstruct;

@Configuration
public class StudentMappers {
  private final ModelMapper modelMapper;

  public StudentMappers(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @PostConstruct
  public void init() {
    personalInfoMapper();
    studentListMapper();
  }

  public void personalInfoMapper() {
    modelMapper
        .typeMap(PersonalInfoForm.class, Student.class)
        .setPostConverter(
            ctx -> {
              Student studentCtx = ctx.getDestination();
              PersonalInfoForm form = ctx.getSource();
              studentCtx.setPermanentAddress(
                  Optional.ofNullable(form.getPermanent_address()).orElse(GenericValues.UNKNOWN));
              studentCtx.setEthnicGroup(
                  Optional.ofNullable(form.getEthnic_group()).orElse(GenericValues.UNKNOWN));
              studentCtx.setCitizenId(
                  Optional.ofNullable(form.getCitizen_id()).orElse(GenericValues.UNKNOWN));
              return studentCtx;
            })
        .addMappings(
            mapper -> {
              mapper.map(PersonalInfoForm::getStudent_id, Student::setStudentId);
              mapper.map(PersonalInfoForm::getFull_name, Student::setFullName);
              mapper.map(PersonalInfoForm::getBirth_date, Student::setBirthDate);
              mapper.map(PersonalInfoForm::getPermanent_address, Student::setPermanentAddress);
              mapper.map(PersonalInfoForm::getTemporary_address, Student::setTemporaryAddress);
              mapper.map(PersonalInfoForm::getEthnic_group, Student::setEthnicGroup);
              mapper.map(PersonalInfoForm::getCitizen_id, Student::setCitizenId);
            });
  }

  public void studentListMapper() {
    modelMapper
        .typeMap(Student.class, StudentList.class)
        .addMappings(
            mapper -> {
              mapper.map(Student::getStudentId, StudentList::setStudent_id);
              mapper.map(Student::getFullName, StudentList::setFull_name);
              mapper.map(Student::getBirthDate, StudentList::setBirth_date);
              mapper.map(Student::getGender, StudentList::setGender);
              mapper.map(src -> src.getMajor().getMajorName(), StudentList::setMajor_name);
              mapper.map(
                  src -> src.getMajor().getDepartment().getDepartmentName(),
                  StudentList::setDepartment_name);
            });
  }
}
