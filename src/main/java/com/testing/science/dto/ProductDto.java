package com.testing.science.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductDto(
        Long id ,
        @NotNull(message = "product name is required")
        String name,
        @NotNull(message = "product description is required")
        String description,
        BigDecimal price,
        Long categoryId
) {
}
