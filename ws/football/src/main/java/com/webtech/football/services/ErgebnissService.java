package com.webtech.football.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtech.football.entities.Ergebniss;
import com.webtech.football.entities.Mannschaft;
import com.webtech.football.repositories.ErgebnissRepository;
import com.webtech.football.repositories.MannschaftRepository;

@Service
public class ErgebnissService {

	@Autowired
	private ErgebnissRepository ergebnissRepository;

	@Autowired
	private MannschaftRepository mannschaftRepository;

	private Ergebniss saveErgebniss(String name1, String name2, int tore1, int tore2, LocalDate datum) {

		Mannschaft mannschaft1 = mannschaftRepository.findByName(name1)
				.orElseThrow(() -> new IllegalArgumentException("Mannschaft not found: " + name1));

		Mannschaft mannschaft2 = mannschaftRepository.findByName(name2)
				.orElseThrow(() -> new IllegalArgumentException("Mannschaft not found: " + name2));

		Ergebniss ergebniss = new Ergebniss();
		ergebniss.setMannschaft1(mannschaft1);
		ergebniss.setMannschaft2(mannschaft2);
		ergebniss.setGruppe1(mannschaft1.getGruppe());
		ergebniss.setGruppe2(mannschaft2.getGruppe());
		ergebniss.setTore1(tore1);
		ergebniss.setTore2(tore2);
		ergebniss.setDatum(datum);

		return ergebnissRepository.save(ergebniss);
	}

}
