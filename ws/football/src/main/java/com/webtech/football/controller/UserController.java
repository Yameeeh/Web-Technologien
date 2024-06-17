package com.webtech.football.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webtech.football.entities.User;
import com.webtech.football.services.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {

	private final AuthenticationManager authenticationManager;
	private UserService userService;

	@Autowired
	public UserController(AuthenticationManager authenticationManager, UserService userService) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
		String username = loginData.get("username");
		String password = loginData.get("password");
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			return ResponseEntity.ok("Login successful");
		} catch (Exception e) {
			return ResponseEntity.status(401).body("Invalid username or password");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Map<String, String> registrationData) {
		String username = registrationData.get("username");
		String email = registrationData.get("email");
		String password = registrationData.get("password");
		try {
			User registeredUser = userService.registerUser(username, password, email);
			return ResponseEntity.ok(registeredUser.getUsername() + " wurde erfolgreich registriert.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
