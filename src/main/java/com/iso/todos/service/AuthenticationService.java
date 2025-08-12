package com.iso.todos.service;

import com.iso.todos.request.RegisterRequest;

public interface AuthenticationService {
    void register(RegisterRequest registerRequest) throws Exception;
}
