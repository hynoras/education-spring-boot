package com.example.education_spring_boot.controller.admin.student;

import com.example.education_spring_boot.model.dto.DefaultResponse;
import com.example.education_spring_boot.model.dto.PaginatedList;
import com.example.education_spring_boot.model.dto.student.detail.IdentityMap;
import com.example.education_spring_boot.model.dto.student.detail.PersonalInfoForm;
import com.example.education_spring_boot.model.dto.student.detail.StudentDetail;
import com.example.education_spring_boot.model.dto.student.list.StudentList;
import com.example.education_spring_boot.service.admin.student.StudentServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/admin")
public class StudentController {

    private final StudentServiceImpl studentService;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

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
        return ResponseEntity.ok(studentService.getAllStudent(
                currentPage,
                pageSize,
                sortBy,
                sortOrder,
                gender,
                major,
                department,
                search
        ));
    }

    @GetMapping("/student/{identity}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StudentDetail> getStudentDetail(@PathVariable("identity") String identity) {
        return ResponseEntity.ok(studentService.getStudentDetail(identity));
    }

    @PostMapping("student")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<DefaultResponse> addStudentPersonalInfo(@Valid @RequestBody PersonalInfoForm personalInfoForm) {
        return ResponseEntity.ok(studentService.addStudentPersonalInfo(personalInfoForm));
    }

    @DeleteMapping("student/{identity}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<DefaultResponse> deleteStudentPersonalInfo(@PathVariable("identity") String identity) {
        return ResponseEntity.ok(studentService.deleteStudentPersonalInfo(identity));
    }

    @DeleteMapping("students")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<DefaultResponse> deleteManyStudentPersonalInfo(@RequestBody List<IdentityMap> identities) {
        return ResponseEntity.ok(studentService.deleteManyStudentPersonalInfo(identities));
    }

    @PutMapping("student/{identity}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<DefaultResponse> updateStudentPersonalInfo(
        @PathVariable("identity") String identity,
        @RequestBody Map<String, Object> request
    ) {
        return ResponseEntity.ok(studentService.updateStudentPersonalInfo(identity, request));
    }

    @PutMapping("student/avatar/{identity}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<DefaultResponse> updateStudentAvatar(
        @RequestParam("avatar") MultipartFile avatar,
        @PathVariable("identity") String identity
    ) throws IOException {
        return ResponseEntity.ok(studentService.updateStudentAvatar(avatar, identity));
    }
}
