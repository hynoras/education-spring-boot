package com.example.education_spring_boot.service.interfaces;

import com.example.education_spring_boot.model.dto.DefaultResponse;
import com.example.education_spring_boot.model.dto.parent.ParentInfoForm;

import java.util.List;
import java.util.Map;

public interface StudentParentService {
    public void addParentInfo(Map<String, Object> addColumns);
    public void updateParentInfo(Map<String, Object> updateColumns);
    public DefaultResponse upsertParentInfo(List<Map<String, Object>> upsertColumns);
    public DefaultResponse deleteParentInfo(List<Map<String, Long>> ids);
}
