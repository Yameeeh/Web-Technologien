package com.webtech.football.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtech.football.entities.Ergebniss;

public interface ErgebnissRepository extends JpaRepository<Ergebniss, Long> {
}
