package com.webtech.football.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtech.football.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);

}
