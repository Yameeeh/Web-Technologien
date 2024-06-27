package com.webtech.football.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {

	private String username;
	private String email;
	private String message;
	private String profilePictureUrl;

}
