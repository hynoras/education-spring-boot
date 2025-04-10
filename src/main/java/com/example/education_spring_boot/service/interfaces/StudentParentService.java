package com.example.education_spring_boot.service.interfaces;

import com.example.education_spring_boot.model.dto.DefaultResponse;
import com.example.education_spring_boot.model.dto.student_parent.ParentInfoForm;

import java.util.List;
import java.util.Map;

public interface StudentParentService {
    public DefaultResponse addParentInfo(List<ParentInfoForm> parentInfoForm);
    public DefaultResponse updateParentInfo(String student_id, String relationship, Map<String, Object> updateColumns);
    public DefaultResponse deleteParentInfo(String student_id);
}
