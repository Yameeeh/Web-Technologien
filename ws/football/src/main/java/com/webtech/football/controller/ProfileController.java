package com.webtech.football.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webtech.football.dto.RegisterDTO;
import com.webtech.football.entities.UserEntity;
import com.webtech.football.services.UserService;

@RequestMapping("/api/profile")
public class ProfileController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<RegisterDTO> getUserProfile(Principal principal) {
		RegisterDTO dto = userService.getUserDTOByUsername(principal.getName());
		return ResponseEntity.ok(dto);
	}

	@PutMapping
	public ResponseEntity<RegisterDTO> updateUserProfile(@RequestBody UserEntity user, Principal principal) {
		UserEntity currentUser = userService.findByUsername(principal.getName());
		currentUser.setUsername(user.getUsername());
		currentUser.setEmail(user.getEmail());
		// Passwort sollte verschlüsselt gespeichert werden
		currentUser.setPassword(user.getPassword());
		userService.save(currentUser);
		return ResponseEntity
				.ok(new RegisterDTO(currentUser.getUsername(), currentUser.getPassword(), currentUser.getEmail()));
	}

}
