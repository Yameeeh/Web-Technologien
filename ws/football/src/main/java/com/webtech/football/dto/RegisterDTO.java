package com.webtech.football.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterDTO {

	private String username;
	private String password;
	private String email;

}
