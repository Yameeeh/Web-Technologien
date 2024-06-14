package com.webtech.football.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.webtech.football.entities.Comment;
import com.webtech.football.entities.FileEntity;
import com.webtech.football.services.CommentService;
import com.webtech.football.services.FileService;

@RestController("/api/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@org.springframework.beans.factory.annotation.Value("${file.upload-dir}")
	private String uploadDir;

	@Autowired
	private FileService fileService;

	@PostMapping
	public ResponseEntity<String> postComment(@RequestParam("comment") String comment,
			@RequestParam("image") MultipartFile image) {

		// Debugging output to check if the data is received correctly
		System.out.println("Received comment: " + comment);
		System.out.println("Received image filename: " + image.getOriginalFilename());

		Comment commentar = commentService.addComment(comment, "userID");

		// Save image
		try {
			FileEntity savedFile = fileService.storeFile(image, commentar.getId());
			System.out.println("File saved with ID: " + savedFile.getId());
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error saving image", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("Comment and image successfully saved", HttpStatus.OK);
	}

}
