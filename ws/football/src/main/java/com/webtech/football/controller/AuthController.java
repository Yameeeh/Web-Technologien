package com.webtech.football.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webtech.football.dto.RegisterDTO;
import com.webtech.football.entities.Role;
import com.webtech.football.entities.UserEntity;
import com.webtech.football.repositories.RoleRepository;
import com.webtech.football.repositories.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	// Wenn der Benutzer erstellt wird soll ihm ein Default Profilbild zugeteilt
	// werden.
	// Es muss in der DB ein entsprechendes File mit dieser ID manuell
	// bereitgestellt werden.
	private static final long DEFAULTFILEID = 999;

	@Autowired
	public AuthController(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;

	}

	// Register Methode
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDTO registrationData) {
		if (userRepository.existsByUsername(registrationData.getUsername())) {
			return new ResponseEntity<>("Der Username ist bereits vergeben.", HttpStatus.BAD_REQUEST);
		}

		UserEntity user = new UserEntity();
		user.setUsername(registrationData.getUsername());
		user.setEmail(registrationData.getEmail());
		user.setPassword(passwordEncoder.encode(registrationData.getPassword()));
		user.setFileID(DEFAULTFILEID);

		Role role = roleRepository.findByName("USER");
		user.setRole(role.getName());

		userRepository.save(user);
		return new ResponseEntity<>("Der Benutzer wurde erfolgreich angelegt.", HttpStatus.OK);
	}

	// War ein Versuch den Auth-Status im Frontend anzuzeigen
	@GetMapping("/status")
	public ResponseEntity<Map<String, Object>> getAuthStatus(Authentication authentication) {
		Map<String, Object> response = new HashMap<>();
		if (authentication != null && authentication.isAuthenticated()) {
			response.put("authenticated", true);
			response.put("username", authentication.getName());
		} else {
			response.put("authenticated", false);
		}
		return ResponseEntity.ok(response);
	}
}
