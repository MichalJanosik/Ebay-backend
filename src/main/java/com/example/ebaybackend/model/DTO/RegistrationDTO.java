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
    private Set<String> roles;

    public RegistrationDTO(User user) {
        id = user.getId();
        username = user.getUsername();
        roles = convertRoles(user);
    }

    private Set<String> convertRoles(User user) {
        Set<String> stringRoles = null;
        if (Objects.nonNull(user.getRoles())) {
            for (var role : user.getRoles()) {
                stringRoles.add(role.getName());
            }
            return stringRoles;
        }
        return null;
    }
}
