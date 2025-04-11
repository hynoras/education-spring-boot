package com.example.education_spring_boot.service.interfaces;

import com.example.education_spring_boot.model.dto.department.DepartmentNameList;

import java.util.List;

public interface DepartmentService {
    List<DepartmentNameList> getAllDepartmentName();
}
