package com.example.education_spring_boot.service.interfaces;

import com.example.education_spring_boot.dto.major.MajorNameList;

import java.util.List;

public interface MajorService {
    List<MajorNameList> getAllMajorName();
}
