package com.example.employeeapi.dto;

public class AuthRequest {
	private String username;
	private String password;

	// ✅ Add public getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
