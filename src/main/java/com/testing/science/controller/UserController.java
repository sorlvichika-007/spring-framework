package com.testing.science.controller;

import com.testing.science.dto.ChangePassword;
import com.testing.science.dto.UpdateUserDto;
import com.testing.science.dto.UserRequest;
import com.testing.science.dto.UserResponse;
import com.testing.science.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest request){
        var reg = userService.registers(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reg);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                   @Valid @RequestBody UpdateUserDto dto){
        var update = userService.update(id,dto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(update);
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(@PathVariable Long id , @Valid @RequestBody ChangePassword password){
        userService.changePassword(id,password);
        return ResponseEntity.noContent().build();
    }

}