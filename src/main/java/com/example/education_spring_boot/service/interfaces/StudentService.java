package com.example.education_spring_boot.service.interfaces;

import com.example.education_spring_boot.dto.student.PaginatedList;
import com.example.education_spring_boot.dto.student.StudentList;
import com.example.education_spring_boot.model.Student;

import java.util.List;

public interface StudentService {
    public PaginatedList<StudentList> getAllStudent(Integer currentPage, Integer pageSize, String sortBy);
    public Student getStudentDetail();
}
