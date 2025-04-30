package com.example.education_spring_boot.shared.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedList<T> {
  private List<T> content;
  private long total_element;
  private int total_page;
  private boolean last;
}
