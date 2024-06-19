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

	@GetMapping("/kader")
	public String kaderEndpoint() {
		return "kader";
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

	@DeleteMapping("/delete")
	public String deleteEndpoint(@RequestBody String s) {
		return "I am deleting " + s;
	}

}