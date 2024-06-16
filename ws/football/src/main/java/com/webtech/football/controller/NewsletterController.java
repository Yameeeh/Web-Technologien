package com.webtech.football.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webtech.football.services.NewsletterService;

@RestController
@RequestMapping("/api/newsletter")
public class NewsletterController {

	@Autowired
	NewsletterService newsletterService;

	@PostMapping
	public ResponseEntity<Map<String, String>> postEmail(@RequestBody Map<String, String> request) {
		String email = request.get("email");
		newsletterService.saveAdresse(email);
		Map<String, String> response = new HashMap<>();
		response.put("message", "Newsletter Registration erfolgreich");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteEmail(@RequestParam("email") String email) {

		newsletterService.deleteAdresse(email);

		return new ResponseEntity<String>("Newsletter Abmeldung erfolgreich", HttpStatus.OK);
	}

}
