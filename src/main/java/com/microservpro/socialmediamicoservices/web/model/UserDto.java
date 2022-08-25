package com.microservpro.socialmediamicoservices.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private UUID id;
    @Size(min = 2, message = "Name should be at least 2 characters")
    private String name;
    @Past(message = "BirthDate should be in the past")
    private LocalDate birthDate;
}
