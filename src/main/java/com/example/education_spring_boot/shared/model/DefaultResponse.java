package com.example.education_spring_boot.shared.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponse {
  private Date timestamp;
  private String message;
  private String detail;
}
