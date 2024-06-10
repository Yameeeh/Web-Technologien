package com.webtech.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ResourceController {
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

	@GetMapping("/admin")
	public String adminEndpoint() {
		return "Admin";
	}

	@GetMapping("/user")
	public String userEndpoint() {
		return "User";
	}

	@GetMapping("/all")
	public String allRolesEndpoint() {
		return "All Roles";
	}

	@DeleteMapping("/delete")
	public String deleteEndpoint(@RequestBody String s) {
		return "I am deleting " + s;
	}
}