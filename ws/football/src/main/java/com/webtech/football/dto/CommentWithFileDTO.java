package com.webtech.football.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentWithFileDTO {
	private Long id;
	private String username;
	private String text;
	private LocalDate time;
	private int topic;
	private String fileName;
	private String fileNameUserPFP;
}