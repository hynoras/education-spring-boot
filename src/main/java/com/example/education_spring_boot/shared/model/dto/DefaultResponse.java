package com.example.education_spring_boot.shared.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponse {
    private Date timestamp;
    private String message;
    private String detail;
}
