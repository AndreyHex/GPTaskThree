package com.gptasktwo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public enum Role {
    ADMIN ("ADMIN"),
    HR ("HR"),
    USER ("USER");

    @Schema(example = "ADMIN")
    private String role;

    Role(String role) {
        this.role = role;
    }
}
