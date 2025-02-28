package com.example.education_spring_boot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "major")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer majorId;

    private String majorName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}

