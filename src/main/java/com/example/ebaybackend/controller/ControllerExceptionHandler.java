package com.example.ebaybackend.controller;

import com.example.ebaybackend.model.DTO.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDTO> handleRuntimeException(RuntimeException exception) {
        ErrorDTO errorDTO = ErrorDTO.builder().error(exception.getMessage()).build();

        return switch (exception.getMessage()) {
            case "Username or password is missing or blank!",
                    "Password must be at least 8 characters long!",
                    "Username already exists!" ->
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
            default -> ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDTO);
        };
    }
}
