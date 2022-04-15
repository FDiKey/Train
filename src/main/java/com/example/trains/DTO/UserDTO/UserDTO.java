package com.example.trains.DTO.UserDTO;

import com.example.trains.domain.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {
    private Long id;
    private String username;
    private Set<String> roles;

    public UserDTO(Long id, String username, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles.stream().map(Role::toString).collect(Collectors.toSet());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
