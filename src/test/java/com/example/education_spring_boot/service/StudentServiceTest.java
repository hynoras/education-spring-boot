package com.example.education_spring_boot.service;

import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.education_spring_boot.features.student.models.dtos.detail.PersonalInfo;
import com.example.education_spring_boot.features.student.repositories.StudentRepo;
import com.example.education_spring_boot.features.student.services.StudentServiceImpl;
import com.example.education_spring_boot.shared.enums.GenderEnum;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
  @InjectMocks private StudentServiceImpl studentService;

  @Mock private StudentRepo studentRepo;

  private PersonalInfo studentPersonalInformation;

  @BeforeEach
  void setUp() {
    studentPersonalInformation =
        PersonalInfo.builder()
            .student_id("2052100")
            .full_name("Quang")
            .birth_date(LocalDate.now())
            .gender(GenderEnum.valueOf("Male"))
            .permanent_address("permanent address sample")
            .temporary_address("temp address sample")
            .ethnic_group("Hoa")
            .religion("Phật Giáo")
            .citizen_id("0020202022020")
            .build();
  }

  @Test
  @DisplayName("Delete student personal information based on student id")
  void deleteStudentInfo_ifStudentIdIsFound() {
    String studentId = "21521000";
    studentService.deleteStudentPersonalInfo(studentId);
    verify(studentRepo).deleteById(studentId);
    System.out.println("Test ran successfully");
  }
}
