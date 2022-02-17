package com.gptasktwo.dto;

public enum Role {
    ADMIN ("ADMIN"),
    HR ("HR"),
    USER ("USER");

    private String role;

    Role(String role) {
        this.role = role;
    }
}
