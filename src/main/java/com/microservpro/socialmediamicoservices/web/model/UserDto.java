package com.microservpro.socialmediamicoservices.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "userTable")
public class UserDto {
    //@JsonIgnore
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;
    @Size(min = 2, message = "Name should be at least 2 characters")
    private String name;
    @Past(message = "BirthDate should be in the past")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<PostDto> posts ;

    public UserDto(UUID uuid, String name, LocalDate birthDate) {
    }
}
