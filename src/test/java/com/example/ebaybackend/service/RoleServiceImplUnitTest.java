package com.example.ebaybackend.service;

import com.example.ebaybackend.model.Role;
import com.example.ebaybackend.repository.RoleRepository;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplUnitTest {

    @Test
    void saveRole() {
        Role role = new Role("ROLE_USER");
        Role savedRole;
        RoleRepository mockedRoleRepository = Mockito.mock(RoleRepository.class);
        Mockito.when(mockedRoleRepository.save(any(Role.class)))
                .thenReturn(role);

        savedRole = mockedRoleRepository.save(role);

        assertEquals(role, savedRole);

    }

    @Test
    void findRoleByName() {
        String roleName = "ROLE_USER";
        Role role = new Role(roleName);
        Optional<Role> roleOptional = Optional.of(role);

        RoleRepository mockedRoleRepository = Mockito.mock(RoleRepository.class);
        Mockito.when(mockedRoleRepository.findRoleByName(roleName)).thenReturn(roleOptional);

        Optional<Role> roleFound = mockedRoleRepository.findRoleByName("ROLE_USER");

        assertEquals(roleName, roleFound.get().getName());
    }
}