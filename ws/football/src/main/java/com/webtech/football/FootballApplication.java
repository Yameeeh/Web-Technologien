package com.webtech.football;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FootballApplication implements CommandLineRunner {

//	private static final String LINK = "localhost:8080";

	@Value("${file.upload-dir}")
	private String uploadDir;

	public static void main(String[] args) {
		SpringApplication.run(FootballApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Create the upload directory if it does not exist
		File uploadDirectory = new File(uploadDir);
		if (!uploadDirectory.exists()) {
			uploadDirectory.mkdirs();
			System.out.println("Upload directory created: " + uploadDirectory.getAbsolutePath());
		}

		// Check if directory is writable
		if (uploadDirectory.canWrite()) {
			System.out.println("Upload directory is writable: " + uploadDirectory.getAbsolutePath());
		} else {
			System.err.println("Upload directory is not writable: " + uploadDirectory.getAbsolutePath());
		}

		// Log the uploadDir value to ensure it is loaded correctly
		System.out.println("Configured upload directory: " + uploadDir);
	}
}
