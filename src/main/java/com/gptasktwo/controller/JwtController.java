package com.gptasktwo.controller;

import com.gptasktwo.dto.JwtRequest;
import com.gptasktwo.dto.JwtResponse;
import com.gptasktwo.service.JwtService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;

    @ApiResponse(responseCode = "200",
                content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = JwtResponse.class)))
    @SecurityRequirements
    @PostMapping("/builder-jwt")
    public ResponseEntity builderJwt(@RequestBody JwtRequest jwtRequest) {
        return ResponseEntity.ok(
                new JwtResponse(jwtService.getJwtToken(jwtRequest)));
    }
}
