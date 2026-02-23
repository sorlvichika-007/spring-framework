package com.testing.science.service;

import com.testing.science.dto.ChangePassword;
import com.testing.science.dto.UpdateUserDto;
import com.testing.science.dto.UserRequest;
import com.testing.science.dto.UserResponse;
import jakarta.validation.Valid;

public interface UserService {
    UserResponse registers(@Valid UserRequest request);

    void changePassword(Long id, @Valid ChangePassword password);

    UserResponse update(Long id, @Valid UpdateUserDto dto);
}
