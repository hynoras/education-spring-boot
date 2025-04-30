package com.example.education_spring_boot.features.parent.controllers;

import com.example.education_spring_boot.shared.model.dto.DefaultResponse;
import com.example.education_spring_boot.features.parent.services.StudentParentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.example.education_spring_boot.shared.constants.AuthConstants.ADMIN_PREAUTHORIZE;
import static com.example.education_spring_boot.shared.constants.ControllerMappings.ADMIN_API_PREFIX;

@RestController
@RequestMapping(ADMIN_API_PREFIX)
public class StudentParentController {
    public final StudentParentServiceImpl studentParentService;

    @Autowired
    public StudentParentController(StudentParentServiceImpl studentParentService) {
        this.studentParentService = studentParentService;
    }

    @PostMapping("parent")
    @PreAuthorize(ADMIN_PREAUTHORIZE)
    public ResponseEntity<DefaultResponse> upsertParentInfo(@RequestBody @Valid List<Map<String, Object>> parentInfoForm) {
        return ResponseEntity.ok(studentParentService.upsertParentInfo(parentInfoForm));
    }

    @DeleteMapping("parent")
    @PreAuthorize(ADMIN_PREAUTHORIZE)
    public ResponseEntity<DefaultResponse> deleteParentInfo(@RequestBody List<Map<String, Long>> ids) {
        return ResponseEntity.ok(studentParentService.deleteParentInfo(ids));
    }
}
