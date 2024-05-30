package com.webtech.football;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FootballApplication {

	private static final String LINK = "localhost:8080";

	public static void main(String[] args) {
		SpringApplication.run(FootballApplication.class, args);
		System.out.println("Link: " + LINK);
	}

}
