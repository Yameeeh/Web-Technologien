package com.webtech.football.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webtech.football.dto.LoginDTO;
import com.webtech.football.dto.RegisterDTO;
import com.webtech.football.entities.Role;
import com.webtech.football.entities.UserEntity;
import com.webtech.football.repositories.RoleRepository;
import com.webtech.football.repositories.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class UserController {

	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserController(AuthenticationManager authenticationManager, UserRepository userRepository,
			RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

//	@Autowired
//	public UserController(AuthenticationManager authenticationManager, UserService userService) {
//		this.authenticationManager = authenticationManager;
//		this.userService = userService;
//	}
//
//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
//		String username = loginData.get("username");
//		String password = loginData.get("password");
//		try {
//			Authentication authentication = authenticationManager
//					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//
//			return ResponseEntity.ok("Login successful");
//		} catch (Exception e) {
//			return ResponseEntity.status(401).body("Invalid username or password");
//		}
//	}
//

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO loginData) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			return ResponseEntity.ok("Login successful");
		} catch (Exception e) {
			return ResponseEntity.status(401).body("Invalid username or password");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDTO registrationData) {
		if (userRepository.existsByUsername(registrationData.getUsername())) {
			return new ResponseEntity<>("Der Username ist bereits vergeben.", HttpStatus.BAD_REQUEST);
		}

		UserEntity user = new UserEntity();
		user.setUsername(registrationData.getUsername());
		user.setEmail(registrationData.getEmail());
		user.setPassword(passwordEncoder.encode(registrationData.getPassword()));

		Role roles = roleRepository.findByName("USER").get();
		user.setRoles(Collections.singletonList(roles));

		userRepository.save(user);
		return new ResponseEntity<>("Der Benutzer wurde erfolgreich angelegt.", HttpStatus.OK);
	}
}
