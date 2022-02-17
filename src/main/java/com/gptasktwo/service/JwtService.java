package com.gptasktwo.service;

import com.gptasktwo.dto.JwtRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {

    private final SecretKey key;
    private final long expirationTime = 5000;

    public JwtService() {
        this.key = Keys.hmacShaKeyFor(System.getenv("SECRET_KEY").getBytes(StandardCharsets.UTF_8));
    }

    public String getJwtToken(JwtRequest jwtRequest) {
        if(jwtRequest.getRoles() == null) jwtRequest.setRoles(new ArrayList<>());
        return generateJwtToken(jwtRequest);
    }

    private String generateJwtToken(JwtRequest jwtRequest) {
        return Jwts.builder()
                .setClaims(Map.of("roles", jwtRequest.getRoles()))
                .setIssuer(jwtRequest.getIss())
                .setSubject(jwtRequest.getSub())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expirationTime))
                .signWith(key)
                .compact();
    }

    public List getRolesFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("roles", List.class);
    }

    public String getIssuerFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getIssuer();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("Invalid JWT token: " + e);
        }
        return false;
    }

    public String parseJwt(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

}