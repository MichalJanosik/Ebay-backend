package com.example.ebaybackend.service;

import com.example.ebaybackend.model.DTO.RegistrationDTO;
import com.example.ebaybackend.model.DTO.UserCredentialsDTO;
import com.example.ebaybackend.model.User;

public interface UserService {
    RegistrationDTO getRegistrationDTO(String username);
    RegistrationDTO saveNewUser(UserCredentialsDTO userCredentialsDTO);
}
