package com.example.education_spring_boot.dto.student.detail;

import com.example.education_spring_boot.enums.GenderEnum;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalInformation {
    private String identity;
    private String full_name;
    private LocalDate birth_date;
    private GenderEnum gender;
    private String permanent_address;
    private String temporary_address;
    private String ethnic_group;
    private String religion;
    private String citizen_id;
    private String priority_group;
}
