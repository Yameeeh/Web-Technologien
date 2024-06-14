package com.webtech.football.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.webtech.football.entities.Comment;
import com.webtech.football.entities.User;
import com.webtech.football.services.CommentService;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;
	@org.springframework.beans.factory.annotation.Value("${file.upload-dir}")
	private String uploadDir;

//	@PostMapping("/api/comments")
//	public void addComment(@RequestBody Comment comment) {
//		commentService.addComment(comment);
//	}

	@PostMapping("/api/comments")
	public ResponseEntity<String> postComment(@RequestParam("comment") String comment,
			@RequestParam("image") MultipartFile image) {

		Comment commentar = new Comment();
		commentar.setId(2L);
		commentar.setText(comment);
		commentar.setUser(new User());
		commentar.setTime(LocalDate.now());

		// Debugging output to check if the data is received correctly
		System.out.println("Received comment: " + comment);
		System.out.println("Received image filename: " + image.getOriginalFilename());

		commentService.addComment(commentar);

		// Save image
		try {
			// Ensure the directory exists
			File uploadDirFile = new File(uploadDir);
			if (!uploadDirFile.exists()) {
				uploadDirFile.mkdirs();
			}

			// Check if directory is writable
			if (uploadDirFile.canWrite()) {
				System.out.println("Upload directory is writable: " + uploadDirFile.getAbsolutePath());
			} else {
				System.err.println("Upload directory is not writable: " + uploadDirFile.getAbsolutePath());
			}

			// Save the file
			File uploadFile = new File(uploadDirFile.getAbsolutePath(), image.getOriginalFilename());
			image.transferTo(uploadFile);
			System.out.println("File saved to: " + uploadFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error saving image", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("Comment and image successfully saved", HttpStatus.OK);
	}

//	@GetMapping("/api/comments")
//	public List<Comment> getAllComments() {
//		return commentService.getAllComments();
//	}

	@GetMapping("/api/comments/{id}")
	public Comment getComment(@PathVariable Long id) {
		return commentService.getComment(id);
	}

}
