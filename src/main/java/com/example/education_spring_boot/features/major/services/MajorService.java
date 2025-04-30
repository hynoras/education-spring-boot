package com.example.education_spring_boot.features.major.services;

import java.util.List;

import com.example.education_spring_boot.features.major.models.dtos.MajorNameList;

public interface MajorService {
  List<MajorNameList> getAllMajorName();
}
