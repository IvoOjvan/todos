package com.iso.todos.service;

import com.iso.todos.request.AuthenticationRequest;
import com.iso.todos.request.RegisterRequest;
import com.iso.todos.response.AuthenticationResponse;

public interface AuthenticationService {
    void register(RegisterRequest registerRequest) throws Exception;
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
