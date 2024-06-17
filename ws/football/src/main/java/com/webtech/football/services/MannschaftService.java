package com.webtech.football.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtech.football.entities.Mannschaft;
import com.webtech.football.repositories.MannschaftRepository;

@Service
public class MannschaftService {

	@Autowired
	private MannschaftRepository mannschaftRepository;

	public Mannschaft saveMannschaft(String name, String gruppe, String flagge) {
		Mannschaft mannschaft = new Mannschaft();

		mannschaft.setName(name);
		mannschaft.setGruppe(gruppe);
		mannschaft.setFlagge(flagge);

		return mannschaftRepository.save(mannschaft);
	}
}
