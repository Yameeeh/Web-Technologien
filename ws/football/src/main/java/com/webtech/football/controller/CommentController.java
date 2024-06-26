package com.webtech.football.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.webtech.football.dto.CommentWithFileDTO;
import com.webtech.football.entities.Comment;
import com.webtech.football.entities.FileEntity;
import com.webtech.football.services.CommentService;
import com.webtech.football.services.FileService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private FileService fileService;

	@Value("${file.upload-dir}")
	private String uploadDir;

	@PostMapping
	public ResponseEntity<String> postComment(@RequestParam("comment") String comment,
			@RequestParam(value = "image", required = false) MultipartFile image,
			@RequestParam("topicId") int topicID) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = null;

		if (authentication != null) {
			if (authentication.getPrincipal() instanceof UserDetails) {
				username = ((UserDetails) authentication.getPrincipal()).getUsername();
			} else {
				username = authentication.getPrincipal().toString();
			}
		}

		if (image == null) {
			Comment kommentar = commentService.addComment(comment, topicID, username);
			return new ResponseEntity<>("Comment successfully saved", HttpStatus.OK);
		} else {

			// Save image
			try {
				FileEntity savedFile = fileService.storeFile(image);
				Comment kommentar = commentService.addComment(comment, topicID, username, savedFile.getId());
				System.out.println("File saved with ID: " + savedFile.getId());
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<>("Error saving image", HttpStatus.INTERNAL_SERVER_ERROR);
			}

			return new ResponseEntity<>("Comment and image successfully saved", HttpStatus.OK);
		}

	}

	@GetMapping("/list")
	public List<CommentWithFileDTO> getAllCommentsByTopic(@RequestParam("topicId") int topic) {
		return commentService.getAllCommentsWithFileByTopic(topic);
	}
}
