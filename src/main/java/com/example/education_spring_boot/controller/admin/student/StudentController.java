package com.example.education_spring_boot.controller.admin.student;

import com.example.education_spring_boot.dto.student.PaginatedList;
import com.example.education_spring_boot.dto.student.StudentList;
import com.example.education_spring_boot.service.admin.student.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class StudentController {

    private final StudentServiceImpl studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PaginatedList<StudentList>> getAllStudent(
        @RequestParam(defaultValue = "0") Integer currentPage,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "identity") String sortBy,
        @RequestParam(defaultValue = "desc") String sortOrder,
        @RequestParam(defaultValue = "", required = false) List<String> gender,
        @RequestParam(defaultValue = "", required = false) List<String> major,
        @RequestParam(defaultValue = "", required = false) List<String> department,
        @RequestParam(defaultValue = "") String search
    ) {
        PaginatedList<StudentList> response = studentService.getAllStudent(
            currentPage,
            pageSize,
            sortBy,
            sortOrder,
            gender,
            major,
            department,
            search
        );
        return ResponseEntity.ok(response);
    }
}
