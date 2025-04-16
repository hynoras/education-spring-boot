package com.example.education_spring_boot.service.interfaces;

import com.example.education_spring_boot.model.dto.DefaultResponse;
import com.example.education_spring_boot.model.dto.parent.ParentInfoForm;

import java.util.List;
import java.util.Map;

public interface StudentParentService {
    public DefaultResponse addParentInfo(List<ParentInfoForm> parentInfoForm);
    public DefaultResponse updateParentInfo(List<Map<String, Object>> updateColumns);
    public DefaultResponse deleteParentInfo(List<Map<String, Long>> ids);
}
