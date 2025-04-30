package com.example.education_spring_boot.features.student.models.dtos.list;

import java.time.LocalDate;

import com.example.education_spring_boot.shared.enums.GenderEnum;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentList {
  private String identity;
  private String full_name;
  private LocalDate birth_date;
  private GenderEnum gender;
  private String major_name;
  private String department_name;
}
