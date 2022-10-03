package com.example.ebaybackend.service;

import com.example.ebaybackend.model.DTO.RegistrationDTO;
import com.example.ebaybackend.model.DTO.UserCredentialsDTO;
import com.example.ebaybackend.model.User;
import com.example.ebaybackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegistrationDTO getRegistrationDTO(String username) {
        User user = userRepository.findByUsername(username).get();
        return new RegistrationDTO(user);
    }

    @Override
    public RegistrationDTO saveNewUser(UserCredentialsDTO userCredentialsDTO) {
        if (userExists(userCredentialsDTO)) {
            return null;
        } else {
            User user = new User();
            user.setUsername(userCredentialsDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userCredentialsDTO.getPassword()));
            user.setRole("ROLE_USER");
            userRepository.save(user);
            return getRegistrationDTO(userCredentialsDTO.getUsername());
        }
    }

    private boolean userExists(UserCredentialsDTO userCredentialsDTO) {
        return userRepository.findByUsername(userCredentialsDTO.getUsername()).isPresent();
    }
}
