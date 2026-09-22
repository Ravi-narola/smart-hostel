package com.smarthostel.auth_service.service;

import com.smarthostel.auth_service.entity.User;

import java.util.List;

public interface AuthService {

    User createUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    User updateUser(Long id, User user);

    void deleteUser(Long id);
}