package com.example.education_spring_boot.controller.admin.student;

import com.example.education_spring_boot.model.dto.PaginatedList;
import com.example.education_spring_boot.model.dto.student.detail.StudentDetail;
import com.example.education_spring_boot.model.dto.student.list.StudentList;
import com.example.education_spring_boot.service.admin.student.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        @RequestParam(name = "currentPage", defaultValue = "0") Integer currentPage,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
        @RequestParam(name = "sortBy", defaultValue = "identity") String sortBy,
        @RequestParam(name = "sortOrder", defaultValue = "desc") String sortOrder,
        @RequestParam(name = "gender", required = false) List<String> gender,
        @RequestParam(name = "major", required = false) List<String> major,
        @RequestParam(name = "department", required = false) List<String> department,
        @RequestParam(name = "search", required = false, defaultValue = "") String search
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

    @PutMapping("students/update/{identity}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateStudentDetail(@PathVariable("identity") String identity,  @RequestBody Map<String, Object> request) {
        String response = studentService.updateStudentDetail(identity, request);
        return ResponseEntity.ok(response);
    }
}
