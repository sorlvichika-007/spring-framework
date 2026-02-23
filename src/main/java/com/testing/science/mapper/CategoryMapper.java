package com.testing.science.mapper;

import com.testing.science.dto.CategoryDto;
import com.testing.science.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryDto dto);
    CategoryDto toDto(Category category);

    @Mapping(target = "id" , ignore = true)
    void updateCategory(CategoryDto categoryDto, @MappingTarget Category category);

}
