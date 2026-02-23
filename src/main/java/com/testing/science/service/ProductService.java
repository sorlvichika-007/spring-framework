package com.testing.science.service;

import com.testing.science.dto.ProductDto;
import jakarta.validation.Valid;

public interface ProductService {
    ProductDto create(ProductDto dto);

    ProductDto update(Long id, @Valid ProductDto dto);
}
