package com.example.education_spring_boot.controller.admin.student;

import com.example.education_spring_boot.dto.PaginatedList;
import com.example.education_spring_boot.dto.student.detail.PersonalInformation;
import com.example.education_spring_boot.dto.student.detail.StudentDetail;
import com.example.education_spring_boot.dto.student.list.StudentList;
import com.example.education_spring_boot.model.Student;
import com.example.education_spring_boot.service.admin.student.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/students/{identity}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StudentDetail> getStudentDetail(@PathVariable("identity") String identity) {
        StudentDetail response = studentService.getStudentDetail(identity);
        return ResponseEntity.ok(response);
    }

}
