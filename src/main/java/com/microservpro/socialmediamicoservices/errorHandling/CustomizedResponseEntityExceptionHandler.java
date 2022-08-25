package com.microservpro.socialmediamicoservices.errorHandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.Objects;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetailsDto> handleAllExceptions(Exception ex, WebRequest request){
            ErrorDetailsDto errorDetails = new ErrorDetailsDto(LocalDate.now(),
                    ex.getMessage(), request.getDescription(false));

            return new ResponseEntity<ErrorDetailsDto>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetailsDto> handleUserNotFoundException(Exception ex, WebRequest request){
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(LocalDate.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ErrorDetailsDto>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String errorMessage = "Error count: " + ex.getErrorCount() +
                ", First Error: " + Objects.requireNonNull(ex.getFieldError()).getDefaultMessage();
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(LocalDate.now(),
                errorMessage, request.getDescription(false));

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }


}
