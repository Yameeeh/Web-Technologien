package com.webtech.football.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtech.football.entities.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
