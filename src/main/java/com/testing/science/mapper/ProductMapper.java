package com.testing.science.mapper;

import com.testing.science.dto.ProductDto;
import com.testing.science.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductDto dto);

    @Mapping(target = "categoryId" , source = "category.id")
    ProductDto toDto(Product product);

    @Mapping(target = "id" , ignore = true)
    void updateProduct(ProductDto dto,@MappingTarget Product product);

}
