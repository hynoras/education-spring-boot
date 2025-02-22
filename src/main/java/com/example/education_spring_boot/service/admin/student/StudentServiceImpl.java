package com.example.education_spring_boot.service.admin.student;

import com.example.education_spring_boot.dto.student.StudentList;
import com.example.education_spring_boot.model.Student;
import com.example.education_spring_boot.repository.StudentRepo;
import com.example.education_spring_boot.service.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public List<StudentList> getAllStudent() {
        try {
            return studentRepo.findAllStudent();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch student list",e);
        }
    }

    @Override
    public Student getStudentDetail() {
        return null;
    }
}
