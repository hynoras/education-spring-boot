package com.example.education_spring_boot.features.department.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.education_spring_boot.features.department.models.dtos.DepartmentNameList;
import com.example.education_spring_boot.features.department.services.DepartmentServiceImpl;

@RestController
@RequestMapping("api")
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
