package com.webtech.football.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webtech.football.entities.FileEntity;
import com.webtech.football.repositories.FileRepository;
import com.webtech.football.services.FileService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/files")
public class FileController {

	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private FileService fileService;

	// File über ID laden
	@GetMapping("/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId, HttpServletRequest request) {
		FileEntity fileEntity = fileRepository.findById(fileId)
				.orElseThrow(() -> new RuntimeException("File not found with id " + fileId));

		Resource resource = new FileSystemResource(fileEntity.getFilePath());

		// FileType
		String contentType;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			throw new RuntimeException("Could not determine file type.");
		}

		// Fallback Type
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
	}

}
