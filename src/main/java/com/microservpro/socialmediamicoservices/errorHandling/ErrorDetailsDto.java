package com.microservpro.socialmediamicoservices.errorHandling;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetailsDto {

    private LocalDate timestamp;
    private String message;
    private String details;
}
