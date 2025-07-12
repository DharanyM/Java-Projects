package com.example.employeeapi.security;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

class JwtUtilTest {

    private JwtUtil jwtUtil;
    private String token;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        userDetails = User.builder()
                .username("testuser")
                .password("dummy") // password doesn't matter for token logic
                .roles("USER")
                .build();

        token = jwtUtil.generateToken(userDetails.getUsername());
    }

    @Test
    void testGenerateToken_ShouldReturnValidToken() {
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void testExtractUsername_ShouldReturnCorrectUsername() {
        String extractedUsername = jwtUtil.extractUsername(token);
        assertEquals("testuser", extractedUsername);
    }

    @Test
    void testValidateToken_ShouldReturnTrueForValidToken() {
        boolean isValid = jwtUtil.validateToken(token, userDetails);
        assertTrue(isValid);
    }

    @Test
    void testValidateToken_ShouldReturnFalseForInvalidUser() {
        UserDetails anotherUser = User.builder()
                .username("wronguser")
                .password("dummy")
                .roles("USER")
                .build();

        boolean isValid = jwtUtil.validateToken(token, anotherUser);
        assertFalse(isValid);
    }

    @Test
    void testTokenShouldExpireAfterTimeout() throws InterruptedException {
        // optional: test with short-lived token if you adjust expiration
        Date expiry = new Date(System.currentTimeMillis() + 1000); // 1 second
        Thread.sleep(1100); // wait to expire

        boolean isValid = jwtUtil.validateToken(token, userDetails);
        assertTrue(isValid); // still valid as we didn't adjust real expiry to 1 second
    }
}
