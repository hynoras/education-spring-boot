package com.example.education_spring_boot.features.parent.services;

import java.util.List;
import java.util.Map;

import com.example.education_spring_boot.shared.model.DefaultResponse;

public interface StudentParentService {
  public void addParentInfo(Map<String, Object> addColumns);

  public void updateParentInfo(Map<String, Object> updateColumns);

  public DefaultResponse upsertParentInfo(List<Map<String, Object>> upsertColumns);

  public DefaultResponse deleteParentInfo(List<Map<String, Long>> ids);
}
