package com.webtech.football.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

//@Entity
//@Table(name = "COMMENT")
@Data
@NoArgsConstructor
public class Comment {

	public Comment(Long id, @NonNull User user, @NonNull String text, @NonNull LocalDate time) {
		this.id = id;
		this.user = user;
		this.text = text;
		this.time = time;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NonNull
	@Column(name = "USER")
	private User user;

	@NonNull
	@Column(name = "TEXT")
	private String text;

	@NonNull
	@Column(name = "TIME")
	private LocalDate time;

	@Column(name = "REFERENCE")
	private Comment reference;

//	@Column(name = "IMAGE")
//	private ImageAttachment image;

}
