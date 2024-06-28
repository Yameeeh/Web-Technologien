package com.webtech.football.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.webtech.football.dto.ProfileDTO;
import com.webtech.football.entities.FileEntity;
import com.webtech.football.entities.UserEntity;
import com.webtech.football.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FileService fileService;

	// Speichere/Überschreibe User
	public void save(UserEntity user) {
		userRepository.save(user);
	}

	// Lade UserInfo für die Profile Seite
	public ProfileDTO getUserDTOByUsername(String username) {
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		ProfileDTO dto = new ProfileDTO();
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		return dto;
	}

	// Existiert Username?
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	// Finde durch Username
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	// PFP Update
	public String updateProfilePicture(String username, MultipartFile file) throws IOException {
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Benutzer nicht gefunden"));

		FileEntity fileEntity = fileService.storeFile(file);
		user.setFileID(fileEntity.getId());
		userRepository.save(user);

		// Rückgabe der URL des gespeicherten Profilbilds
		return "/api/files/" + fileEntity.getId();
	}

}
