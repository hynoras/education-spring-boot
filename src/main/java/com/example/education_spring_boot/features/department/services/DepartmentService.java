package com.example.education_spring_boot.features.department.services;

import com.example.education_spring_boot.features.department.models.dtos.DepartmentNameList;

import java.util.List;

public interface DepartmentService {
    List<DepartmentNameList> getAllDepartmentName();
}
