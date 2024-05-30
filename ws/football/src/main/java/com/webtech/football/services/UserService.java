package com.webtech.football.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webtech.football.entities.User;
import com.webtech.football.repositories.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User registerUser(String username, String password) {
		// Check if username is already taken
		if (userRepository.existsByUsername(username)) {
			throw new IllegalArgumentException("Username is already taken.");
		}

		// Encode the password before saving
		String encodedPassword = passwordEncoder.encode(password);

		// Create a new user entity
		User user = new User();
		user.setUsername(username);
		user.setPassword(encodedPassword);

		// Save the user in the database
		return userRepository.save(user);
	}

	public boolean authenticate(String username, String password) {
		// Retrieve the user by username
		User user = userRepository.findByUsername(username);

		// Check if the user exists and the password matches
		return user != null && passwordEncoder.matches(password, user.getPassword());
	}

}
