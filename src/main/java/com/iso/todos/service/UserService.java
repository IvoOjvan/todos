package com.iso.todos.service;

import com.iso.todos.response.UserResponse;

public interface UserService {
    UserResponse getUserInfo();
    void deleteUser();
}
