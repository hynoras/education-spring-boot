package com.example.education_spring_boot.controller.admin.student;

import com.example.education_spring_boot.model.dto.DefaultResponse;
import com.example.education_spring_boot.model.dto.student_parent.ParentInfoForm;
import com.example.education_spring_boot.service.admin.student.StudentParentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.example.education_spring_boot.constants.AuthConstants.ADMIN_PREAUTHORIZE;
import static com.example.education_spring_boot.constants.ControllerMappings.ADMIN_API_PREFIX;

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
    public ResponseEntity<DefaultResponse> addParentInfo(@RequestBody @Valid List<ParentInfoForm> parentInfoForm) {
        return ResponseEntity.ok(studentParentService.addParentInfo(parentInfoForm));
    }

    @PutMapping("parent")
    @PreAuthorize(ADMIN_PREAUTHORIZE)
    public ResponseEntity<DefaultResponse> updateParentInfo(@RequestBody List<Map<String, Object>> updateColumns) {
        return ResponseEntity.ok(studentParentService.updateParentInfo(updateColumns));
    }

    @DeleteMapping("parent")
    @PreAuthorize(ADMIN_PREAUTHORIZE)
    public ResponseEntity<DefaultResponse> deleteParentInfo(@RequestBody List<Map<String, Long>> ids) {
        return ResponseEntity.ok(studentParentService.deleteParentInfo(ids));
    }
}
