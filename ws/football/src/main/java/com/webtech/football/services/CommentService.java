package com.webtech.football.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtech.football.entities.Comment;
import com.webtech.football.repositories.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public Comment addComment(String text, String userID) {
		Comment comment = new Comment();
		comment.setText(text);
		comment.setTime(LocalDate.now());
		comment.setUserID(userID);
		return commentRepository.save(comment);
	}

	public Comment saveComment(Comment comment) {
		return commentRepository.save(comment);
	}

}
