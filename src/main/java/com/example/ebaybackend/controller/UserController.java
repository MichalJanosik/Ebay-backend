package com.example.ebaybackend.controller;

import com.example.ebaybackend.model.User;
import com.example.ebaybackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.getRegistrationDTO());
    }
}
