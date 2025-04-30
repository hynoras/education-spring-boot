package com.example.education_spring_boot.features.student.models.dtos.detail;

import java.time.LocalDate;

import com.example.education_spring_boot.features.auth.models.entities.Account;
import com.example.education_spring_boot.features.location.models.entities.Location;
import com.example.education_spring_boot.features.location.utils.LocationDeserializer;
import com.example.education_spring_boot.features.major.models.entities.Major;
import com.example.education_spring_boot.features.major.utils.MajorDeserializer;
import com.example.education_spring_boot.shared.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalInfoForm {
  @NotEmpty(message = "Student ID must not empty")
  private String identity;

  private Account account;

  @NotEmpty(message = "Student name must not empty")
  private String full_name;

  private LocalDate birth_date;

  private GenderEnum gender;

  private String permanent_address;

  private String temporary_address;

  private String ethnic_group;

  private String religion;

  private String citizen_id;

  private String avatar;

  @JsonDeserialize(using = LocationDeserializer.class)
  @JsonProperty("province")
  private Location province;

  @JsonDeserialize(using = MajorDeserializer.class)
  @JsonProperty("major")
  private Major major;
}
