package com.example.ebaybackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    //>>>>>>>>>>>>>>Constructors>>>>>>>>>>>>>>>>>>>>>>>>>>

    public Role(String name) {
        this.name = name;
    }

    //>>>>>>>>>>>>>>>Methods>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public void addUser(User user) {
        users.add(user);
    }
}
