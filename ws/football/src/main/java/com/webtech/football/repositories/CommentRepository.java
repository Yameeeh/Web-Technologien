package com.webtech.football.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webtech.football.entities.Comment;
import com.webtech.football.entities.User;

public class CommentRepository {

	@Repository
	public interface UserRepository extends JpaRepository<Comment, Long> {

		// Custom query method to find comments by username
		User findByUsername(String username);

		// Custom query method to find comments by Comment-ID
		User findByID(Long id);

	}

}
