package com.example.education_spring_boot.model.dto.student.detail;

import com.example.education_spring_boot.enums.GenderEnum;
import com.example.education_spring_boot.model.entity.Account;
import com.example.education_spring_boot.model.entity.Location;
import com.example.education_spring_boot.model.entity.Major;
import com.example.education_spring_boot.util.JSONUtil.LocationDeserializer;
import com.example.education_spring_boot.util.JSONUtil.MajorDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

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

    private LocalDate date_of_birth;

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
