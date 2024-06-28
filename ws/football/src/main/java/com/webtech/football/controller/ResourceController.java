package com.webtech.football.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResourceController {

	@GetMapping("/login")
	public String loginEndpoint() {
		return "login";
	}

	@GetMapping("/kader")
	public String kaderEndpoint() {
		return "kader";
	}

	@GetMapping("/stadien")
	public String stadienEndpoint() {
		return "stadien";
	}

	@GetMapping("/forum")
	public String forumEndpoint(Authentication auth) {
		return "forum";
	}

	@GetMapping("/ergebnisse")
	public String ergebnisseEndpoint() {
		return "ergebnisse";
	}

	@GetMapping("/profile")
	public String profileEndpoint() {
		return "profile";
	}
}