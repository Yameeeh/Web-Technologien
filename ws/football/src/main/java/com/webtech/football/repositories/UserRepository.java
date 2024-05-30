package com.webtech.football.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webtech.football.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// Custom query method to find a user by username
	User findByUsername(String username);

	// Custom query method to check if a username exists
	boolean existsByUsername(String username);

}
