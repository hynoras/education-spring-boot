package com.example.education_spring_boot.service.interfaces;

import com.example.education_spring_boot.enums.GenderEnum;
import com.example.education_spring_boot.model.dto.PaginatedList;
import com.example.education_spring_boot.model.dto.student.detail.StudentDetail;
import com.example.education_spring_boot.model.dto.student.list.StudentList;
import org.springframework.web.multipart.MultipartFile;

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
    public String updateStudentDetail(String identity, Map<String, Object> updateColumns);
    public String updateStudentAvatar(String identity, MultipartFile image);
}
