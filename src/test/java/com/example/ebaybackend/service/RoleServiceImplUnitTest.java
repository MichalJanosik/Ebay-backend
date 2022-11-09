package com.example.ebaybackend.service;

import com.example.ebaybackend.model.Role;
import com.example.ebaybackend.repository.RoleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplUnitTest {

    private static RoleRepository mockedRoleRepository;

    @BeforeEach
    public void initialSetup() {
        mockedRoleRepository = Mockito.mock(RoleRepository.class);
    }

    @Test
    void saveRole_OK() {
        Role role = new Role("ROLE_USER");
        Role savedRole;

        Mockito.when(mockedRoleRepository.save(any(Role.class)))
                .thenReturn(role);

        savedRole = mockedRoleRepository.save(role);

        assertEquals(role, savedRole);

    }

    @Test
    void findRoleByName_OK() {
        String roleName = "ROLE_USER";
        Role role = new Role(roleName);
        Optional<Role> roleOptional = Optional.of(role);

        Mockito.when(mockedRoleRepository.findRoleByName(roleName))
                .thenReturn(roleOptional);

        Optional<Role> roleFound = mockedRoleRepository.findRoleByName("ROLE_USER");

        assertEquals(roleName, roleFound.get().getName());
    }

    @Test
    void findRoleByName_NULL() {
        Optional<Role> expected = Optional.empty();

        Optional<Role> roleFound = mockedRoleRepository.findRoleByName(null);

        assertEquals(expected, roleFound);
    }
}