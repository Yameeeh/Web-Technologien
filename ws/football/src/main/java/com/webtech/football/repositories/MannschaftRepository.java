package com.webtech.football.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webtech.football.entities.Mannschaft;

@Repository
public interface MannschaftRepository extends JpaRepository<Mannschaft, Long> {

	Optional<Mannschaft> findByName(String name);

}
