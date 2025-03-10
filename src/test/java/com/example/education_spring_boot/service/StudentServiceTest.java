package com.example.education_spring_boot.service;

import com.example.education_spring_boot.dto.student.detail.PersonalInformation;
import com.example.education_spring_boot.enums.GenderEnum;
import com.example.education_spring_boot.model.Student;
import com.example.education_spring_boot.repository.StudentRepo;
import com.example.education_spring_boot.service.admin.student.StudentServiceImpl;
import com.example.education_spring_boot.service.interfaces.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepo studentRepo;

    private PersonalInformation studentPersonalInformation;

    @BeforeEach
    void setUp() {
        studentPersonalInformation = PersonalInformation.builder()
                .identity("2052100")
                .full_name("Quang")
                .birth_date(LocalDate.now())
                .gender(GenderEnum.valueOf("Male"))
                .permanent_address("permanent address sample")
                .temporary_address("temp address sample")
                .ethnic_group("Hoa")
                .religion("Phật Giáo")
                .citizen_id("0020202022020")
                .priority_group("Vùng sâu")
                .build();

        Mockito.when(studentRepo.findAllByIdentity("2052100"))
                .thenReturn(studentPersonalInformation);
    }

    @Test
    @DisplayName("Student personal information based on identity")
    void fetchStudentDetail_ifStudentIdentityIsFound() {
        String identity = "2052100";
        PersonalInformation actual = studentService.getStudentDetail(identity);
        System.out.println(actual);
        assertEquals(identity, actual.getIdentity());
    }
}