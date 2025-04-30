package com.example.education_spring_boot.features.parent.models.dtos;

import java.time.LocalDate;

import com.example.education_spring_boot.shared.enums.ParentRelationshipEnum;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentInfo {
  private Long id;
  private String full_name;
  private LocalDate birth_date;
  private String nationality;
  private String permanent_address;
  private ParentRelationshipEnum relationship;
}
