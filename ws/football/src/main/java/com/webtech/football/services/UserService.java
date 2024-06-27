package com.webtech.football.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.webtech.football.dto.RegisterDTO;
import com.webtech.football.entities.UserEntity;
import com.webtech.football.repositories.UserRepository;

public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void save(UserEntity user) {
		userRepository.save(user);
	}

	public RegisterDTO getUserDTOByUsername(String username) {
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		RegisterDTO dto = new RegisterDTO(user.getUsername(), user.getPassword(), user.getEmail());
		return dto;
	}

	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}
