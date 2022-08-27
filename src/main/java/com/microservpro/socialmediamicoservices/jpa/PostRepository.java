package com.microservpro.socialmediamicoservices.jpa;

import com.microservpro.socialmediamicoservices.web.model.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostDto, UUID> {

}
