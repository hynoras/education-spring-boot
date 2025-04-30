package com.example.education_spring_boot.features.major.services;

import com.example.education_spring_boot.features.major.models.dtos.MajorNameList;

import java.util.List;

public interface MajorService {
    List<MajorNameList> getAllMajorName();
}
