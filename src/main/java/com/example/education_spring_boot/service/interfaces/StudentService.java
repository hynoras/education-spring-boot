package com.example.education_spring_boot.service.interfaces;

import com.example.education_spring_boot.enums.GenderEnum;
import com.example.education_spring_boot.model.dto.DefaultResponse;
import com.example.education_spring_boot.model.dto.PaginatedList;
import com.example.education_spring_boot.model.dto.student.detail.PersonalInfoForm;
import com.example.education_spring_boot.model.dto.student.detail.StudentDetail;
import com.example.education_spring_boot.model.dto.student.list.StudentList;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface StudentService {
    public PaginatedList<StudentList> getAllStudent(
        Integer currentPage,
        Integer pageSize,
        String sortBy,
        String sortOrder,
        List<String> gender,
        List<String> major,
        List<String> department,
        String search
    );
    public StudentDetail getStudentDetail(String identity);
    public DefaultResponse addStudentPersonalInfo(PersonalInfoForm personalInfoForm);
    public DefaultResponse updateStudentPersonalInfo(String identity, Map<String, Object> updateColumns);
    public DefaultResponse updateStudentAvatar(MultipartFile avatar, String identity) throws IOException;
    public DefaultResponse deleteStudentPersonalInfo(String identity);
}
