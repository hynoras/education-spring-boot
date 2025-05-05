package com.example.education_spring_boot.features.major.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.education_spring_boot.features.major.models.dtos.MajorNameList;
import com.example.education_spring_boot.features.major.services.MajorServiceImpl;

@RestController
@RequestMapping("api")
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
