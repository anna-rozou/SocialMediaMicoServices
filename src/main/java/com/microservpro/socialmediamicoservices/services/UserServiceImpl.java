package com.microservpro.socialmediamicoservices.services;

import com.microservpro.socialmediamicoservices.errorHandling.UserNotFoundException;
import com.microservpro.socialmediamicoservices.web.model.UserDto;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Service
public class UserServiceImpl implements UserService {

    private static List<UserDto> users = new ArrayList<>();

    static{
        users.add( new UserDto(UUID.fromString("14330fba-1a87-46f9-9225-a181e22d1f5f") ,"Anna Rozou", LocalDate.now().minusYears(26)));
        users.add( new UserDto(UUID.fromString("eb27d57e-5273-44c9-8dd1-f9caedd046dc") ,"Jim Rozos", LocalDate.now().minusYears(20)));
        users.add( new UserDto(UUID.fromString("7994e0be-b4ed-4fa4-b150-6d01cd16ca29") ,"Linos Rozos", LocalDate.now().minusYears(28)));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return users;
    }

    @Override
    public UserDto getUserDetails(UUID userId) {
            return users.stream().filter(a -> a.getId().equals(userId)).findFirst().orElse(null);
    }

    @Override
    public UserDto createUser(UserDto user) {
        user.setId(UUID.randomUUID());
        users.add(user);
        return user;
    }

    @Override
    public void deleteUser(UUID userId) {
        //UserDto user2delete = users.stream().filter(a -> a.getId().equals(userId)).findFirst().get();

        Predicate<? super UserDto> predicate = user -> user.getId().equals(userId);
        users.removeIf(predicate);
    }
}
