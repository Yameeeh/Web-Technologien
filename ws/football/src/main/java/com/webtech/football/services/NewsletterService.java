package com.webtech.football.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtech.football.entities.NewsletterAdresse;
import com.webtech.football.repositories.NewsletterRepository;

@Service
public class NewsletterService {

	@Autowired
	private NewsletterRepository newsletterRepository;

	// Adresse dem Newsletter hinzufügen
	public NewsletterAdresse saveAdresse(String email) {

		NewsletterAdresse newsletterAdresse = new NewsletterAdresse();
		newsletterAdresse.setEmail(email);

		return newsletterRepository.save(newsletterAdresse);
	}

	// Adresse dem Newsletter entfernen
	public void deleteAdresse(String email) {

		newsletterRepository.deleteByEmail(email);

	}

}
