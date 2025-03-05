package com.example.education_spring_boot.service.admin.student;

import com.example.education_spring_boot.dto.student.major.MajorNameList;
import com.example.education_spring_boot.repository.MajorRepo;
import com.example.education_spring_boot.service.interfaces.MajorService;
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
