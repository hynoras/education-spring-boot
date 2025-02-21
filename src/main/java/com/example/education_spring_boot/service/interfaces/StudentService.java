package com.example.education_spring_boot.service.interfaces;

import com.example.education_spring_boot.model.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudent();
    public Student getStudentDetail();
}
