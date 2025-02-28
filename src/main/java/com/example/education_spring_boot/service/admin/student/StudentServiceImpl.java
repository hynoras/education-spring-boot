package com.example.education_spring_boot.service.admin.student;

import com.example.education_spring_boot.dto.student.PaginatedList;
import com.example.education_spring_boot.dto.student.StudentList;
import com.example.education_spring_boot.model.Student;
import com.example.education_spring_boot.repository.StudentRepo;
import com.example.education_spring_boot.service.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public PaginatedList<StudentList> getAllStudent(Integer currentPage, Integer pageSize, String sortBy) {
        try {
            Pageable paging = PageRequest.of(currentPage, pageSize, Sort.by(sortBy));
            Page<StudentList> pagedResult = studentRepo.findAllStudent(paging);
            return new PaginatedList<>(
                pagedResult.getContent(),
                pagedResult.getTotalElements(),
                pagedResult.getTotalPages(),
                pagedResult.isLast()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch student list",e);
        }
    }

    @Override
    public Student getStudentDetail() {
        return null;
    }
}
