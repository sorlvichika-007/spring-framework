package com.testing.science.mapper;

import com.testing.science.dto.UpdateUserDto;
import com.testing.science.dto.UserRequest;
import com.testing.science.dto.UserResponse;
import com.testing.science.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);
    User toEntity(UserRequest request);
    void updateUser(UpdateUserDto dto,@MappingTarget User user);
}