package com.example.employeeapi.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET = "thisIsASecretKeyWithAtLeast32Characters!"; // Must be 32+ chars for HS256
    private static final long EXPIRATION_TIME = 86400000; // 1 day

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String token, String username) {
        try {
            Claims claims = extractClaims(token);
            return username.equals(claims.getSubject()) && !isTokenExpired(claims.getExpiration());
        } catch (Exception e) {
            return false; // Invalid token, expired, malformed, etc.
        }
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
