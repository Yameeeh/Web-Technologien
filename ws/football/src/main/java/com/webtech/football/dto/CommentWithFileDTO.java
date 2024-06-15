package com.webtech.football.dto;

import java.time.LocalDate;

import com.webtech.football.entities.FileEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentWithFileDTO {
	private Long id;
	private String userID;
	private String text;
	private LocalDate time;
	private String topic;
	private FileEntity file;
}