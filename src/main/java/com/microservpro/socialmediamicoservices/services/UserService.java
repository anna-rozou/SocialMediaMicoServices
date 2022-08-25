package com.microservpro.socialmediamicoservices.services;

import com.microservpro.socialmediamicoservices.web.model.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserDetails(UUID userId);

    UserDto createUser(UserDto user);

    void deleteUser(UUID userId);
}
