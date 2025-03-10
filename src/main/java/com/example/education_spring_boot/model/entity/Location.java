package com.example.education_spring_boot.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer provinceId;

    private String provinceName;
    private String districtName;
    private boolean isRemoteArea;
}

