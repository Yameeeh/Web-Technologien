package com.webtech.football.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<String> postEmail(@RequestParam("email") String email) {

		newsletterService.saveAdresse(email);

		return new ResponseEntity<>("Newsletter Registration erfolgreich", HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteEmail(@RequestParam("email") String email) {

		newsletterService.deleteAdresse(email);

		return new ResponseEntity<>("Newsletter Abmeldung erfolgreich", HttpStatus.OK);
	}

}
