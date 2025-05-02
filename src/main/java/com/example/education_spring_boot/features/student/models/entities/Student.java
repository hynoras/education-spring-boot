package com.example.education_spring_boot.features.student.models.entities;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import com.example.education_spring_boot.features.auth.models.entities.Account;
import com.example.education_spring_boot.features.location.models.entities.Location;
import com.example.education_spring_boot.features.major.models.entities.Major;
import com.example.education_spring_boot.features.parent.models.entities.StudentParent;
import com.example.education_spring_boot.features.student.constants.StudentColumns;
import com.example.education_spring_boot.features.student.constants.StudentTables;
import com.example.education_spring_boot.shared.constants.database.CommonColumnNames;
import com.example.education_spring_boot.shared.constants.generic.SqlDefaults;
import com.example.education_spring_boot.shared.enums.GenderEnum;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = StudentTables.NAME)
public class Student {
  @Id
  @Column(name = StudentColumns.IDENTITY)
  private String identity;

  @OneToOne
  @JoinColumn(name = StudentColumns.ACCOUNT_ID, unique = true)
  private Account account;

  @Column(name = CommonColumnNames.FULL_NAME, nullable = false, unique = true)
  private String fullName;

  @Column(name = CommonColumnNames.BIRTH_DATE, columnDefinition = "DATE")
  private LocalDate birthDate;

  @Enumerated(EnumType.STRING)
  @Column(name = CommonColumnNames.GENDER)
  private GenderEnum gender;

  @Column(name = CommonColumnNames.PERMANENT_ADDRESS, nullable = false)
  @ColumnDefault(SqlDefaults.UNKNOWN)
  private String permanentAddress;

  @Column(name = StudentColumns.TEMPORARY_ADDRESS)
  private String temporaryAddress;

  @Column(name = StudentColumns.ETHNIC_GROUP, length = 100, nullable = false)
  @ColumnDefault(SqlDefaults.UNKNOWN)
  private String ethnicGroup;

  @Column(name = StudentColumns.RELIGION, length = 100, nullable = true)
  @ColumnDefault(SqlDefaults.NONE)
  private String religion;

  @Column(name = StudentColumns.CITIZEN_ID, length = 20, nullable = false, unique = true)
  @ColumnDefault(SqlDefaults.UNKNOWN)
  private String citizenId;

  @Column(name = StudentColumns.AVATAR)
  private String avatar;

  @ManyToOne
  @JoinColumn(name = "province_id", referencedColumnName = "provinceid")
  private Location province;

  @ManyToOne
  @JoinColumn(name = "major_id", referencedColumnName = "majorid")
  private Major major;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "studentId")
  private List<StudentParent> parent;
}
