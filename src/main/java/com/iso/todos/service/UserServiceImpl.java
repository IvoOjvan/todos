package com.iso.todos.service;

import com.iso.todos.entity.Authority;
import com.iso.todos.entity.User;
import com.iso.todos.repository.UserRepository;
import com.iso.todos.response.UserResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserInfo() {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

       if(authentication == null || !authentication.isAuthenticated() ||
               authentication.getPrincipal().equals("anonymousUser")){
           throw new AccessDeniedException("Authentication required");
       }

       User user =  (User) authentication.getPrincipal();

       return new UserResponse(
               user.getId(),
               user.getFirstName() + " " + user.getLastName(),
               user.getEmail(),
               user.getAuthorities().stream().map(auth -> (Authority) auth).toList()
       );
    }
}
