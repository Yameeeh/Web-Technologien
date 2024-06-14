package com.webtech.football;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FootballApplication implements CommandLineRunner {

	@Value("${file.upload-dir}")
	private String uploadDir;

	public static void main(String[] args) {
		SpringApplication.run(FootballApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		File uploadDirectory = new File(uploadDir);
		if (!uploadDirectory.exists()) {
			uploadDirectory.mkdirs();
			System.out.println("Upload directory created: " + uploadDirectory.getAbsolutePath());
		}
	}
}
