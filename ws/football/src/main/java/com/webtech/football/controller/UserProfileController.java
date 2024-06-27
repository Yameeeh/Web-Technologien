package com.webtech.football.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.webtech.football.dto.ProfileDTO;
import com.webtech.football.entities.UserEntity;
import com.webtech.football.services.CommentService;
import com.webtech.football.services.UserService;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private CommentService commentService;

	@GetMapping
	public ResponseEntity<ProfileDTO> getUserProfile(Principal principal) {
		ProfileDTO dto = userService.getUserDTOByUsername(principal.getName());
		return ResponseEntity.ok(dto);
	}

	@PutMapping
	public ResponseEntity<ProfileDTO> updateUserProfile(@RequestBody UserEntity user, Principal principal) {
		UserEntity currentUser = userService.findByUsername(principal.getName());

		ProfileDTO dto = new ProfileDTO();

		// Prüfen, ob der Benutzername bereits existiert
		if (user.getUsername() != null && !user.getUsername().equals(currentUser.getUsername())) {
			if (userService.existsByUsername(user.getUsername())) {
				dto.setUsername(currentUser.getUsername());
				dto.setMessage("Benutzername existiert bereits");
				return ResponseEntity.status(HttpStatus.CONFLICT).body(dto);
			}
			commentService.updateCommentUsername(user.getUsername(), currentUser.getUsername());
			currentUser.setUsername(user.getUsername());

		}

		if (user.getEmail() != null) {
			currentUser.setEmail(user.getEmail());
		}

		// Nur aktualisieren, wenn ein neues Passwort angegeben wird
		if (user.getPassword() != null && !user.getPassword().isEmpty()) {
			currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
		}

		userService.save(currentUser);
		dto.setUsername(currentUser.getUsername());
		dto.setEmail(currentUser.getEmail());
		dto.setMessage("Profil aktualisiert!");
		return ResponseEntity.ok(dto);
	}

	@PostMapping("/picture")
	public ResponseEntity<ProfileDTO> updateProfilePicture(@RequestParam("profilePicture") MultipartFile file,
			Principal principal) {
		ProfileDTO dto = new ProfileDTO();
		try {
			String username = principal.getName();
			// Speichere das Bild und aktualisiere den Benutzer in der Datenbank
			String profilePictureUrl = userService.updateProfilePicture(username, file);
			dto.setProfilePictureUrl(profilePictureUrl);
			dto.setMessage("Profilbild erfolgreich aktualisiert");
			return ResponseEntity.ok(dto);
		} catch (IOException e) {
			dto.setMessage("Fehler beim Hochladen des Profilbilds");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
		}
	}

}
