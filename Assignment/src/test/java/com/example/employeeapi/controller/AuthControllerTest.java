package com.example.employeeapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.example.employeeapi.dto.AuthRequest;
import com.example.employeeapi.security.AuthController;
import com.example.employeeapi.security.JwtService;

class AuthControllerTest {

	@InjectMocks
	private AuthController authController;

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private JwtService jwtService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testLogin_Successful() {
		// Arrange
		AuthRequest request = new AuthRequest();
		request.setUsername("user");
		request.setPassword("password");

		String expectedToken = "mocked-jwt-token";

		when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
				.thenReturn(mock(org.springframework.security.core.Authentication.class));

		when(jwtService.generateToken("user")).thenReturn(expectedToken);

		// Act
		ResponseEntity<?> response = authController.login(request);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Map<?, ?> body = (Map<?, ?>) response.getBody();
		assertNotNull(body);
		assertEquals(expectedToken, body.get("token"));

		// Verify interactions
		verify(authenticationManager, times(1)).authenticate(any());
		verify(jwtService, times(1)).generateToken("user");
	}
}
