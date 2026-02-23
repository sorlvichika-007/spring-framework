package com.testing.science.service;

import com.testing.science.dto.CategoryDto;
import jakarta.validation.Valid;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(@Valid CategoryDto dto);

    List<CategoryDto> getAll();

    CategoryDto update(@Valid CategoryDto dto, Long id);
}
