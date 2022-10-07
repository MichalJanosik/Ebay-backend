package com.example.ebaybackend.service;

import com.example.ebaybackend.model.Role;

import java.util.Optional;

public interface RoleService {

    void saveRole(Role role);
    Optional<Role> findRoleByName(String name);
}
