package com.microservpro.socialmediamicoservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.microservpro.socialmediamicoservices.web.model.UserDto;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDto, UUID> {


}
