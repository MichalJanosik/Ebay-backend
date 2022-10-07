package com.example.ebaybackend.model.DTO;

import com.example.ebaybackend.model.Role;
import com.example.ebaybackend.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class RegistrationDTO {
    private Long id;
    private String username;
    private List<String> roles;

    public RegistrationDTO(User user) {
        id = user.getId();
        username = user.getUsername();
        roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }
}
