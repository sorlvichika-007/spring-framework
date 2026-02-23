package com.testing.science.service.impl;

import com.testing.science.dto.ChangePassword;
import com.testing.science.dto.UpdateUserDto;
import com.testing.science.dto.UserRequest;
import com.testing.science.dto.UserResponse;
import com.testing.science.exceptions.BadRequestException;
import com.testing.science.exceptions.ResourceNotFoundException;
import com.testing.science.mapper.UserMapper;
import com.testing.science.model.Role;
import com.testing.science.repository.UserRepository;
import com.testing.science.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse registers(UserRequest request) {
        var user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public void changePassword(Long id, ChangePassword request) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!passwordEncoder.matches(request.oldPassword(),user.getPassword())){
            throw new BadRequestException("Old password not match");
        }

        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);
    }

    @Override
    public UserResponse update(Long id, UpdateUserDto dto) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userMapper.updateUser(dto,user);
        return userMapper.toResponse(userRepository.save(user));
    }
}