package com.example.education_spring_boot.features.student.models.entities;

import static com.example.education_spring_boot.features.student.constants.StudentAttributes.*;
import static com.example.education_spring_boot.shared.constants.DatabaseAttributes.GENDER;
import static com.example.education_spring_boot.shared.constants.GenericValues.NONE_DB_DEFAULT;
import static com.example.education_spring_boot.shared.constants.GenericValues.UNKNOWN_DB_DEFAULT;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import com.example.education_spring_boot.features.auth.models.entities.Account;
import com.example.education_spring_boot.features.location.models.entities.Location;
import com.example.education_spring_boot.features.major.models.entities.Major;
import com.example.education_spring_boot.features.parent.models.entities.StudentParent;
import com.example.education_spring_boot.shared.enums.GenderEnum;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = STUDENT_ATTR)
public class Student {
  @Id
  @Column(name = IDENTITY_ATTR)
  private String identity;

  @OneToOne
  @JoinColumn(name = ACCOUNT_ID_ATTR, unique = true)
  private Account account;

  @Column(name = FULL_NAME_ATTR, nullable = false, unique = true)
  private String fullName;

  @Column(name = BIRTH_DATE_ATTR, columnDefinition = "DATE")
  private LocalDate birthDate;

  @Enumerated(EnumType.STRING)
  @Column(name = GENDER)
  private GenderEnum gender;

  @Column(name = PERMANENT_ADDRESS_ATTR, nullable = false)
  @ColumnDefault(UNKNOWN_DB_DEFAULT)
  private String permanentAddress;

  @Column(name = TEMPORARY_ADDRESS_ATTR)
  private String temporaryAddress;

  @Column(name = ETHNIC_GROUP_ATTR, length = 100, nullable = false)
  @ColumnDefault(UNKNOWN_DB_DEFAULT)
  private String ethnicGroup;

  @Column(name = RELIGION_ATTR, length = 100, nullable = true)
  @ColumnDefault(NONE_DB_DEFAULT)
  private String religion;

  @Column(name = CITIZEN_ID_ATTR, length = 20, nullable = false, unique = true)
  @ColumnDefault(UNKNOWN_DB_DEFAULT)
  private String citizenId;

  @Column(name = AVATAR_ATTR)
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
