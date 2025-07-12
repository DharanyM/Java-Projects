package com.example.employeeapi.security;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("admin".equals(username)) {
			return User.builder().username("admin")
					.password("$2a$10$Y3QFVPZhU66Gq/GkgCj2cuERl4M3Uu1PlZzY3BJOVW1j5j0HjPhc6") // password admin
					.roles("USER").build();
		}
		throw new UsernameNotFoundException("User not found");
	}
}
