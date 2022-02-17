package com.gptasktwo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class JwtRequest {

    private String iss;
    private String sub;
    private List<String> roles;

}
