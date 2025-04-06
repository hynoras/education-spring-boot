package com.example.education_spring_boot.service;

import com.example.education_spring_boot.model.dto.student.detail.PersonalInfo;
import com.example.education_spring_boot.enums.GenderEnum;
import com.example.education_spring_boot.repository.StudentRepo;
import com.example.education_spring_boot.service.admin.student.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepo studentRepo;

    private PersonalInfo studentPersonalInformation;

    @BeforeEach
    void setUp() {
        studentPersonalInformation = PersonalInfo.builder()
                .identity("2052100")
                .full_name("Quang")
                .date_of_birth(LocalDate.now())
                .gender(GenderEnum.valueOf("Male"))
                .permanent_address("permanent address sample")
                .temporary_address("temp address sample")
                .ethnic_group("Hoa")
                .religion("Phật Giáo")
                .citizen_id("0020202022020")
                .build();

        Mockito.when(studentRepo.findByIdentity("2052100"))
                .thenReturn(studentPersonalInformation);
    }

//    @Test
//    @DisplayName("Student personal information based on identity")
//    void fetchStudentDetail_ifStudentIdentityIsFound() {
//        String identity = "2052100";
//        StudentDetail actual = studentService.getStudentDetail(identity);
//        System.out.println(actual);
//        assertEquals(identity, actual.getPersonal_information());
//    }
}