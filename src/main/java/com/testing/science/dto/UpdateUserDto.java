package com.testing.science.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserDto(
        @NotBlank(message = "Name is required")
        @Size(max = 255, message = "Name must be less than 255 characters")
        String username,
        @NotBlank(message = "Email is required")
        @Email(message = "Email must valid")
        String email) {
}
