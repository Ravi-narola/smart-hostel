package com.smarthostel.auth_service.mapper;

import com.smarthostel.auth_service.dto.UserRequest;
import com.smarthostel.auth_service.dto.UserResponse;
import com.smarthostel.auth_service.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .mobileNumber(request.getMobileNumber())
                .role(request.getRole())
                .enabled(request.getEnabled() != null
                        ? request.getEnabled()
                        : true)
                .build();
    }

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .role(user.getRole())
                .enabled(user.getEnabled())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public void updateEntity(User user, UserRequest request) {
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        if (request.getPassword() != null &&
                !request.getPassword().isBlank()) {
            user.setPassword(request.getPassword());
        }

        user.setMobileNumber(request.getMobileNumber());
        user.setRole(request.getRole());

        if (request.getEnabled() != null) {
            user.setEnabled(request.getEnabled());
        }
    }
}