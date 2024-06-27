package com.webtech.football.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.webtech.football.entities.FileEntity;
import com.webtech.football.repositories.FileRepository;

@Service
public class FileService {

	@Value("${file.upload-dir}")
	private String uploadDir;

	@Autowired
	private FileRepository fileRepository;

	public FileEntity storeFile(MultipartFile file) throws IOException {
		String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		String filePath = uploadDir + "/" + fileName;

		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = file.getInputStream()) {
			Files.copy(inputStream, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ex) {
			throw new IOException("Could not save file " + fileName, ex);
		}

		FileEntity fileEntity = new FileEntity();
		fileEntity.setFileName(fileName);
		fileEntity.setFilePath(filePath);
		fileEntity.setFileType(file.getContentType());

		return fileRepository.save(fileEntity);
	}
}
