package com.example.employeeapi.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    if ("user".equals(username)) {
	        return User.builder()
	                .username("user")
	                .password(passwordEncoder().encode("password")) // BCrypt-hashed
	                .roles("USER")
	                .build();
	    }
	    throw new UsernameNotFoundException("User not found");
	}

	// Add this method to get encoder
	private PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
