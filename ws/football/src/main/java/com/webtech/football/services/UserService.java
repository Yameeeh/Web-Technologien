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

	public void save(UserEntity user) {
		userRepository.save(user);
	}

	public ProfileDTO getUserDTOByUsername(String username) {
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		ProfileDTO dto = new ProfileDTO();
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		return dto;
	}

	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	public String updateProfilePicture(String username, MultipartFile file) throws IOException {
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Benutzer nicht gefunden"));

		// Speichere das Profilbild
		FileEntity fileEntity = fileService.storeFile(file);

		// Aktualisiere die fileID des Benutzers
		user.setFileID(fileEntity.getId());
		userRepository.save(user);

		// Rückgabe der URL des gespeicherten Profilbilds
		return "/api/files/" + fileEntity.getId();
	}

}
