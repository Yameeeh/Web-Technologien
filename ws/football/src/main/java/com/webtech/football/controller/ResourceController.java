package com.webtech.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ResourceController {

//	@GetMapping("/")
//	public String startEndpoint() {
//		return "index";
//	}

	@GetMapping("/login")
	public String loginEndpoint() {
		return "login";
	}

	@GetMapping("/stadien")
	public String stadienEndpoint() {
		return "stadien";
	}

	@GetMapping("/forum")
	public String forumEndpoint() {
		return "forum";
	}

	@GetMapping("/ergebnisse")
	public String ergebnisseEndpoint() {
		return "ergebnisse";
	}

	@GetMapping("/all")
	public String allRolesEndpoint() {
		return "All Roles";
	}

	@GetMapping("/api/comments")
	public String commentsEndpoint() {
		return "forum";
	}

	@DeleteMapping("/delete")
	public String deleteEndpoint(@RequestBody String s) {
		return "I am deleting " + s;
	}

}