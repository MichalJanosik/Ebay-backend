package com.example.ebaybackend.service;

import com.example.ebaybackend.model.DTO.RegistrationDTO;
import com.example.ebaybackend.model.DTO.UserCredentialsDTO;
import com.example.ebaybackend.model.Role;
import com.example.ebaybackend.model.User;
import com.example.ebaybackend.repository.UserRepository;
import com.example.ebaybackend.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegistrationDTO getRegistrationDTO(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        return user.map(RegistrationDTO::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }

    @Override
    public RegistrationDTO saveNewUser(UserCredentialsDTO userCredentialsDTO) {
        if (userExists(userCredentialsDTO)) {
            return null;
        } else {
            User user = new User();
            user.setUsername(userCredentialsDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userCredentialsDTO.getPassword()));
            user.setRoles(new HashSet<>());

            Optional<Role> persistedRole = roleService.findRoleByName("ROLE_USER");
            if (persistedRole.isPresent()) {
                addRoleToUser(user, persistedRole.get());
            } else {
                Role role = new Role("ROLE_USER");
                roleService.saveRole(role);
                addRoleToUser(user, role);
            }

            userRepository.save(user);
            return getRegistrationDTO(userCredentialsDTO.getUsername());
        }
    }

    public void addRoleToUser(User user, Role role) {
        user.addRole(role);
    }

    private boolean userExists(UserCredentialsDTO userCredentialsDTO) {
        return userRepository.findByUsername(userCredentialsDTO.getUsername()).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);

        return user.map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }
}
