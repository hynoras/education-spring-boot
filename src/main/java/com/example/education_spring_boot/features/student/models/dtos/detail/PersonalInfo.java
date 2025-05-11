package com.example.education_spring_boot.features.student.models.dtos.detail;

import java.time.LocalDate;

import com.example.education_spring_boot.shared.enums.GenderEnum;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalInfo {
  private String identity;
  private String full_name;
  private LocalDate birth_date;
  private GenderEnum gender;
  private String permanent_address;
  private String temporary_address;
  private String ethnic_group;
  private String religion;
  private String citizen_id;
  private String avatar;
}
