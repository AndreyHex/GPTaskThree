package com.gptasktwo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class JwtResponse {

    @Schema(example = "xxxxxxx.yyyyyyy.zzzzzzz")
    private final String token;

}
