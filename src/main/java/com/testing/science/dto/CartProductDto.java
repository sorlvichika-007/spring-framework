package com.testing.science.dto;

import java.math.BigDecimal;

public record CartProductDto(
        Long id,
        String name,
        BigDecimal price
) {
}
