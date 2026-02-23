package com.testing.science.service.impl;

import com.testing.science.dto.CategoryDto;
import com.testing.science.exceptions.ResourceNotFoundException;
import com.testing.science.mapper.CategoryMapper;
import com.testing.science.repository.CategoryRepository;
import com.testing.science.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto createCategory(CategoryDto dto) {
        var category = categoryMapper.toEntity(dto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getAll() {
        var category = categoryRepository.findAll();
        return category.stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto update(CategoryDto dto, Long id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        categoryMapper.updateCategory(dto,category);
        return categoryMapper.toDto(category);
    }
}