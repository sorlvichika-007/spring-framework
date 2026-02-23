package com.testing.science.dto;

import java.math.BigDecimal;

public record CartItemDto(
        CartProductDto product,
        Integer quantity,
        BigDecimal totalPrice
) {
}