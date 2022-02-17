package com.gptasktwo.controller;

import com.gptasktwo.dto.JwtRequest;
import com.gptasktwo.dto.JwtResponse;
import com.gptasktwo.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;

    @PostMapping("/builder-jwt")
    public ResponseEntity builderJwt(@RequestBody JwtRequest jwtRequest) {
        return ResponseEntity.ok(
                new JwtResponse(jwtService.getJwtToken(jwtRequest)));
    }
}
