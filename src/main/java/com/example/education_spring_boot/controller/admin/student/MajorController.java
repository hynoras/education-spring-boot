package com.example.education_spring_boot.controller.admin.student;

import com.example.education_spring_boot.dto.student.major.MajorNameList;
import com.example.education_spring_boot.service.admin.student.MajorServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class MajorController {

    private final MajorServiceImpl majorService;

    public MajorController(MajorServiceImpl majorService) {
        this.majorService = majorService;
    }

    @GetMapping("/majors")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<MajorNameList>> getAllMajorName() {
        List<MajorNameList> response = majorService.getAllMajorName();
        return ResponseEntity.ok(response);
    }
}
