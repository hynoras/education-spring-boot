package com.example.education_spring_boot.controller.admin.student;

import com.example.education_spring_boot.model.dto.department.DepartmentNameList;
import com.example.education_spring_boot.service.admin.student.DepartmentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<DepartmentNameList>> getAllStudent() {
        List<DepartmentNameList> response = departmentService.getAllDepartmentName();
        return ResponseEntity.ok(response);
    }
}
