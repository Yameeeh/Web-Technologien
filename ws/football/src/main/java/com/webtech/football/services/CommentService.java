package com.webtech.football.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtech.football.dto.CommentWithFileDTO;
import com.webtech.football.entities.Comment;
import com.webtech.football.entities.FileEntity;
import com.webtech.football.entities.UserEntity;
import com.webtech.football.repositories.CommentRepository;
import com.webtech.football.repositories.FileRepository;
import com.webtech.football.repositories.UserRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private UserRepository userRepository;

	public Comment addComment(String text, int topic, String username, long fileId) {
		Comment comment = new Comment();
		comment.setText(text);
		comment.setTime(LocalDate.now());
		comment.setUsername(username);
		comment.setTopic(topic);
		comment.setFileID(fileId);
		return commentRepository.save(comment);
	}

	public Comment addComment(String text, int topic, String username) {
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

	public List<Comment> getAllCommentsByUsername(String username) {
		return commentRepository.getAllCommentsByUsername(username);
	}

	public List<CommentWithFileDTO> getAllCommentsWithFileByTopic(int topic) {
		List<Comment> comments = commentRepository.getAllCommentsByTopic(topic);

		return comments.stream().map(comment -> {
			// Bild für das Kommentar finden
			Optional<FileEntity> file = fileRepository.findById(comment.getFileID());
			String fileName = file.map(FileEntity::getFileName).orElse(null);

			// User Profile Picture für das Kommentar finden
			Optional<UserEntity> user = userRepository.findByUsername(comment.getUsername());
			Optional<FileEntity> userPFP = fileRepository.findById(user.map(UserEntity::getFileID).orElse(null));
			String fileNameUserPFP = userPFP.map(FileEntity::getFileName).orElse(null);

			return new CommentWithFileDTO(comment.getId(), comment.getUsername(), comment.getText(), comment.getTime(),
					comment.getTopic(), fileName, fileNameUserPFP);
		}).collect(Collectors.toList());
	}

	public int updateCommentUsername(String newUsername, String currentUsername) {
		return commentRepository.updateUsername(newUsername, currentUsername);
	}
}
