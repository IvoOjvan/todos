package com.iso.todos.service;

import com.iso.todos.entity.Authority;
import com.iso.todos.entity.User;
import com.iso.todos.repository.UserRepository;
import com.iso.todos.response.UserResponse;
import com.iso.todos.util.FindAuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final FindAuthenticatedUser findAuthenticatedUser;

    public UserServiceImpl(UserRepository userRepository, FindAuthenticatedUser findAuthenticatedUser) {
        this.userRepository = userRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserInfo() {
       User user = findAuthenticatedUser.getAutheticatedUser();

       return new UserResponse(
               user.getId(),
               user.getFirstName() + " " + user.getLastName(),
               user.getEmail(),
               user.getAuthorities().stream().map(auth -> (Authority) auth).toList()
       );
    }

    @Override
    public void deleteUser() {
        User user = findAuthenticatedUser.getAutheticatedUser();

        if(isLastAdmin(user)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin cannot delete itself");
        }

        userRepository.delete(user);
    }

    private boolean isLastAdmin(User user){
        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(authority -> "ROLE_ADMIN".equals(authority.getAuthority()));

        if(isAdmin){
            long adminCount = userRepository.countAdminUsers();
            return adminCount <= 1;
        }

        return false;
    }
}
