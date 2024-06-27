package com.webtech.football.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webtech.football.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> getAllCommentsByTopic(int topic);

	List<Comment> getAllCommentsByUsername(String username);

	@Modifying
	@Transactional
	@Query("UPDATE Comment c SET c.username = :newUsername WHERE c.username = :currentUsername")
	int updateUsername(String newUsername, String currentUsername);

}
