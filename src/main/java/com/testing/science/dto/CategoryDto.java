package com.testing.science.dto;

import jakarta.validation.constraints.NotNull;

public record CategoryDto(
        Long id,
        @NotNull(message = "category name is required") String name)
{

}
