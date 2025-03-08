package com.example.education_spring_boot.service.interfaces;

import com.example.education_spring_boot.dto.PaginatedList;
import com.example.education_spring_boot.dto.student.detail.ParentInformation;
import com.example.education_spring_boot.dto.student.detail.PersonalInformation;
import com.example.education_spring_boot.dto.student.detail.StudentDetail;
import com.example.education_spring_boot.dto.student.list.StudentList;
import com.example.education_spring_boot.model.Student;

import java.util.List;

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
}
