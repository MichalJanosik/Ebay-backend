package com.example.ebaybackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;

    @ManyToMany
    @JoinTable(name = "usersRoles",
        joinColumns = @JoinColumn(name = "userId"),
        inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    private Set<Role> roles;

    //>>>>>>>>>>>>>>Constructors>>>>>>>>>>>>>>>>>>>>>>>>>>

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //>>>>>>>>>>>>>>>Methods>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public void addRole(Role role) {
        roles.add(role);
    }
}
