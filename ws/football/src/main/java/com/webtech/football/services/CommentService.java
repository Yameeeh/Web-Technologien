package com.webtech.football.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.webtech.football.dto.CommentWithFileDTO;
import com.webtech.football.entities.Comment;
import com.webtech.football.entities.FileEntity;
import com.webtech.football.repositories.CommentRepository;
import com.webtech.football.repositories.FileRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private FileRepository fileRepository;

	public Comment addComment(String text, int topic) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = ((User) authentication.getPrincipal()).getUsername();

		Comment comment = new Comment();
		comment.setText(text);
		comment.setTime(LocalDate.now());
		comment.setUsername(username);
		comment.setTopic(topic);
		return commentRepository.save(comment);
	}

	public Comment saveComment(Comment comment) {
		return commentRepository.save(comment);
	}

	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}

	public List<Comment> getAllCommentsByTopic(int topic) {
		return commentRepository.getAllCommentsByTopic(topic);
	}

	public List<CommentWithFileDTO> getAllCommentsWithFileByTopic(int topic) {
		List<Comment> comments = commentRepository.getAllCommentsByTopic(topic);

		return comments.stream().map(comment -> {
			FileEntity file = fileRepository.findByCommentID(comment.getId());
			String fileName = (file != null) ? file.getFileName() : null;
			return new CommentWithFileDTO(comment.getId(), comment.getUsername(), comment.getText(), comment.getTime(),
					comment.getTopic(), fileName);
		}).collect(Collectors.toList());
	}
}
