package com.microservpro.socialmediamicoservices.web.controller;

import com.microservpro.socialmediamicoservices.errorHandling.UserNotFoundException;
import com.microservpro.socialmediamicoservices.services.UserService;
import com.microservpro.socialmediamicoservices.web.model.UserDto;
import com.microservpro.socialmediamicoservices.web.model.UserDtoV2;
import org.apache.catalina.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/user")
@RestController
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserDto> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{userId}", params = "version1")
    //@ResponseStatus(HttpStatus.FOUND)
    public UserDto getUserV1(@PathVariable("userId") UUID userId){

        UserDto returnedUser = userService.getUserDetails(userId);
        if (returnedUser == null){
            throw new UserNotFoundException("UserNotFound userId: " + userId);
        }
        return returnedUser;
    }

    @GetMapping(path = "/{userId}", params = "version2")
    @ResponseStatus(HttpStatus.FOUND)
    public UserDtoV2 getUserV2(@PathVariable("userId") UUID userId){
        return new UserDtoV2(UUID.fromString("33674db0-ceda-47cc-a7b1-fbb8c81ba587") ,"Anna", "Iliop", LocalDate.now().minusYears(28));
    }

    @PostMapping("/users")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
        UserDto savedUser = userService.createUser(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") UUID userId){

        userService.deleteUser(userId);
    }

}
