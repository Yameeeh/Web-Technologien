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
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "ID")
	private String id;

	@NonNull
	@Column(name = "USERNAME", unique = true, nullable = false)
	private String username;

	@NonNull
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@NonNull
	@Column(name = "EMAIL", nullable = false)
	private String email;
}
