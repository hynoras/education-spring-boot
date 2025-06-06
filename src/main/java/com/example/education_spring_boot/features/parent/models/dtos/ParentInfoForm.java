package com.example.education_spring_boot.features.parent.models.dtos;

import java.time.LocalDate;

import com.example.education_spring_boot.features.student.models.entities.Student;
import com.example.education_spring_boot.features.student.utils.StudentDeserializer;
import com.example.education_spring_boot.shared.enums.ParentRelationshipEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentInfoForm {
  @NotNull(message = "Student ID must not be null")
  @JsonDeserialize(using = StudentDeserializer.class)
  private Student student_id;

  @NotNull(message = "Parent name must not empty")
  private String full_name;

  @NotNull(message = "Parent date of birth must not empty")
  private LocalDate birth_date;

  @NotNull(message = "Parent nationality must not empty")
  private String nationality;

  @NotNull(message = "Parent permanent address must not empty")
  private String permanent_address;

  private ParentRelationshipEnum relationship;
}
