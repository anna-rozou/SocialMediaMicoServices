package com.microservpro.socialmediamicoservices.web.controller;

import com.microservpro.socialmediamicoservices.errorHandling.UserNotFoundException;
import com.microservpro.socialmediamicoservices.jpa.PostRepository;
import com.microservpro.socialmediamicoservices.jpa.UserRepository;
import com.microservpro.socialmediamicoservices.web.model.PostDto;
import com.microservpro.socialmediamicoservices.web.model.UserDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/api/v1/jpa/user")
@RestController
public class UserJpaController {
    UserRepository userRepository;
    PostRepository postRepository;

    public UserJpaController(UserRepository userRepository, PostRepository postRepository) {

        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserDto> getAll(){

        return userRepository.findAll();
    }

    @GetMapping(path = "/{userId}"/*, params = "version1"*/)
    //@ResponseStatus(HttpStatus.FOUND)
    public EntityModel<UserDto> getUser(@PathVariable("userId") UUID userId){

        Optional<UserDto> returnedUser = userRepository.findById(userId);
        if (!returnedUser.isPresent()){
            throw new UserNotFoundException("UserNotFound userId: " + userId);
        }

        EntityModel<UserDto> entityModel = EntityModel.of(returnedUser.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAll());

        entityModel.add(link.withRel("allUsers"));

        return entityModel;
    }

    @PostMapping("/users")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
        UserDto savedUser = userRepository.save(user);

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

        userRepository.deleteById(userId);
    }

    @GetMapping("/{userId}/posts")
    public List<PostDto> getUsersPosts(@PathVariable("userId") UUID userId){
        Optional<UserDto> returnedUser = userRepository.findById(userId);
        if (!returnedUser.isPresent()){
            throw new UserNotFoundException("UserNotFound userId: " + userId);
        }

        return returnedUser.get().getPosts();
    }

    @PostMapping("/{userId}/post")
    public ResponseEntity<PostDto> createPost4User(@PathVariable("userId") UUID userId, @Valid @RequestBody PostDto post){

        Optional<UserDto> returnedUser = userRepository.findById(userId);
        if (!returnedUser.isPresent()){
            throw new UserNotFoundException("UserNotFound userId: " + userId);
        }

        post.setUser(returnedUser.get());
        PostDto savedPost = postRepository.save(post);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{postId}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);

    }

}
