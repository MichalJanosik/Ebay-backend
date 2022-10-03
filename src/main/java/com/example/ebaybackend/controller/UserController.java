package com.example.ebaybackend.controller;

import com.example.ebaybackend.model.DTO.RegistrationDTO;
import com.example.ebaybackend.model.DTO.UserCredentialsDTO;
import com.example.ebaybackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<Object> registerUser(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        if (Objects.nonNull(userCredentialsDTO)
                && Objects.nonNull(userCredentialsDTO.getUsername())
                && Objects.nonNull(userCredentialsDTO.getPassword())
                && !userCredentialsDTO.getUsername().isBlank()
                && !userCredentialsDTO.getPassword().isBlank()
        ) {
            throw new RuntimeException("Username or password is missing or blank!");
        }
        if (userCredentialsDTO.getPassword().length() > 8) {
            throw new RuntimeException("Password must be at least 8 characters long!");
        }

        RegistrationDTO registrationDTO = userService.saveNewUser(userCredentialsDTO);
        if (Objects.isNull(registrationDTO)) {
            throw new RuntimeException("Username already exists!");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registrationDTO);
    }
}
