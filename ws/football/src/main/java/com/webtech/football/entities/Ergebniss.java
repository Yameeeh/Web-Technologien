package com.webtech.football.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ERGEBNISSE")
@Data
@NoArgsConstructor
public class Ergebniss {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "MANNSCHAFT1_NAME", referencedColumnName = "NAME", nullable = false)
	private Mannschaft mannschaft1;

	@ManyToOne
	@JoinColumn(name = "MANNSCHAFT2_NAME", referencedColumnName = "NAME", nullable = false)
	private Mannschaft mannschaft2;

	@OneToOne
	@JoinColumn(name = "MANNSCHAFT1_GRUPPE", referencedColumnName = "NAME", nullable = false)
	private char gruppe1;

	@OneToOne
	@JoinColumn(name = "MANNSCHAFT2_GRUPPE", referencedColumnName = "NAME", nullable = false)
	private char gruppe2;

	@Column(name = "Tore1")
	private int tore1;

	@Column(name = "Tore2")
	private int tore2;

	@Column(name = "Datum")
	private LocalDate datum;

}
