package com.gptasktwo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class JwtRequest {

    @Schema(example = "GP")
    private String iss;
    @Schema(example = "task3")
    private String sub;
    private List<Role> roles;

}
