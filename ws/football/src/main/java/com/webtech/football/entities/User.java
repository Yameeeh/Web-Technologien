package com.webtech.football.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor
public class User {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@NonNull
	@Column(name = "USERNAME", unique = true)
	private String username;

	@NonNull
	@Column(name = "PASSWORD")
	private String password;

	@NonNull
	@Column(name = "EMAIL")
	private String email;

}
