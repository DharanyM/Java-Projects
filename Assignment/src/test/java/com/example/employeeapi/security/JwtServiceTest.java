package com.example.employeeapi.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @Test
    void testGenerateAndExtractToken() {
        String username = "testUser";

        // Generate token
        String token = jwtService.generateToken(username);
        assertNotNull(token);

        // Extract username
        String extracted = jwtService.extractUsername(token);
        assertEquals(username, extracted);
    }

    @Test
    void testValidateToken_ValidToken() {
        String username = "validUser";
        String token = jwtService.generateToken(username);

        assertTrue(jwtService.validateToken(token, username));
    }

    @Test
    void testValidateToken_InvalidUsername() {
        String token = jwtService.generateToken("user1");

        assertFalse(jwtService.validateToken(token, "user2"));
    }
}
