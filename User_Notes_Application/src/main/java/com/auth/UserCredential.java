package com.auth;

import java.security.Principal;
import java.util.Set;

public class UserCredential implements Principal {
    private final String name;

    private final Set<String> roles;

    public UserCredential(String name) {
        this.name = name;
        this.roles = null;
    }

    public UserCredential(String name, Set<String> roles) {
        this.name = name;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return (int) (Math.random() * 100);
    }

    public Set<String> getRoles() {
        return roles;
    }
}
