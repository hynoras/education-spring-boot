package com.example.education_spring_boot.shared.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
