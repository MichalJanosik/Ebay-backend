package com.example.ebaybackend.model.DTO;

import com.example.ebaybackend.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class RegistrationDTO {
    private Long id;
    private String username;
    private String roles;

    public RegistrationDTO(User user) {
        id = user.getId();
        username = user.getUsername();
        roles = user.getRole();
    }
}
