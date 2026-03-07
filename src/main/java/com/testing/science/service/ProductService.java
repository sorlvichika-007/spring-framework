package com.testing.science.service;

import com.testing.science.dto.ProductDto;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductDto dto);

    ProductDto update(Long id, @Valid ProductDto dto);

    List<ProductDto> getAllProduct();

    ProductDto getById(Long id);
}
