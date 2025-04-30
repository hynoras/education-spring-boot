package com.example.education_spring_boot.features.major.services;

import com.example.education_spring_boot.features.major.models.dtos.MajorNameList;
import com.example.education_spring_boot.features.major.repositories.MajorRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {

    private final MajorRepo majorRepo;

    public MajorServiceImpl(MajorRepo majorRepo) {
        this.majorRepo = majorRepo;
    }

    @Override
    public List<MajorNameList> getAllMajorName() {
        return majorRepo.findMajorName();
    }
}
