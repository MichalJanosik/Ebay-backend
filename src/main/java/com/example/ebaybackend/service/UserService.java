package com.example.ebaybackend.service;

import com.example.ebaybackend.model.DTO.RegistrationDTO;
import com.example.ebaybackend.model.DTO.UserCredentialsDTO;

public interface UserService {
    RegistrationDTO getRegistrationDTO(String username);
    RegistrationDTO saveNewUser(UserCredentialsDTO userCredentialsDTO);
}
