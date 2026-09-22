package com.smarthostel.auth_service.controller;

import com.smarthostel.auth_service.dto.UserRequest;
import com.smarthostel.auth_service.dto.UserResponse;
import com.smarthostel.auth_service.entity.User;
import com.smarthostel.auth_service.mapper.UserMapper;
import com.smarthostel.auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/users")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody UserRequest request) {

        User user = userMapper.toEntity(request);
        User savedUser = authService.createUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userMapper.toResponse(savedUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userMapper.toResponse(
                        authService.getUserById(id)));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return ResponseEntity.ok(
                authService.getAllUsers()
                        .stream()
                        .map(userMapper::toResponse)
                        .toList());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getByUsername(
            @PathVariable String username) {

        return ResponseEntity.ok(
                userMapper.toResponse(
                        authService.getUserByUsername(username)));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getByEmail(
            @PathVariable String email) {

        return ResponseEntity.ok(
                userMapper.toResponse(
                        authService.getUserByEmail(email)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest request) {

        User user = userMapper.toEntity(request);

        User updatedUser = authService.updateUser(id, user);

        return ResponseEntity.ok(
                userMapper.toResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id) {

        authService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}