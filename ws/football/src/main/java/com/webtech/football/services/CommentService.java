package com.webtech.football.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.webtech.football.entities.Comment;
import com.webtech.football.entities.User;

@Service
public class CommentService {

	private List<Comment> comments = new ArrayList<Comment>(
			Arrays.asList(new Comment(1l, new User(), "ein Testkomentar", LocalDate.now())));

	public List<Comment> getAllComments() {
		return comments;
	}

	public Comment getComment(Long id) {
		return comments.stream().filter(t -> t.getId().equals(id)).findFirst().get();
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}

}
