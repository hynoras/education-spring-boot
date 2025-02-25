package com.example.education_spring_boot.dto.student;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentList {
    private Long identity;
    private String full_name;
    private String major_name;
    private String department_name;
}
