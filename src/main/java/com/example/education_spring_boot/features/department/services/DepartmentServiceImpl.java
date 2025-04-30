package com.example.education_spring_boot.features.department.services;

import com.example.education_spring_boot.features.department.models.dtos.DepartmentNameList;
import com.example.education_spring_boot.features.department.repositories.DepartmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepo departmentRepo;

    public DepartmentServiceImpl(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }


    @Override
    public List<DepartmentNameList> getAllDepartmentName() {
        return departmentRepo.findDepartmentName();
    }
}
