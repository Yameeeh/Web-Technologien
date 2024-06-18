package com.webtech.football.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
			@RequestParam("image") MultipartFile image, @RequestParam("topicId") int topicID) {

		Comment kommentar = commentService.addComment(comment, topicID);

		// Save image
		try {
			FileEntity savedFile = fileService.storeFile(image, kommentar.getId());
			System.out.println("File saved with ID: " + savedFile.getId());
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error saving image", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("Comment and image successfully saved", HttpStatus.OK);
	}

	@GetMapping("/list")
	public List<CommentWithFileDTO> getAllCommentsByTopic(@RequestParam("topicId") int topic) {
		return commentService.getAllCommentsWithFileByTopic(topic);
	}
}
