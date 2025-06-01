package com.example.education_spring_boot.features.student.models.dtos.detail;

import java.time.LocalDate;

import com.example.education_spring_boot.features.auth.models.entities.Account;
import com.example.education_spring_boot.features.auth.utils.AccountDeserializer;
import com.example.education_spring_boot.features.major.models.entities.Major;
import com.example.education_spring_boot.features.major.utils.MajorDeserializer;
import com.example.education_spring_boot.shared.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalInfoForm {
  @NotEmpty(message = "Student ID must not be empty")
  private String student_id;

  @JsonDeserialize(using = AccountDeserializer.class)
  @JsonProperty("account")
  private Account account;

  @NotEmpty(message = "Student name must not be empty")
  private String full_name;

  @Past(message = "Date must be in the past")
  @NotNull(message = "Birth Date must not be empty")
  private LocalDate birth_date;

  private GenderEnum gender;

  @NotEmpty(message = "Permanent address must not be empty")
  private String permanent_address;

  private String temporary_address;

  private String ethnic_group;

  private String religion;

  @NotEmpty(message = "Citizen ID must not be empty")
  private String citizen_id;

  @JsonDeserialize(using = MajorDeserializer.class)
  @JsonProperty("major")
  private Major major;
}
