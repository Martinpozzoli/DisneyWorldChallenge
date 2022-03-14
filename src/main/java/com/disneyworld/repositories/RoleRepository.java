package com.disneyworld.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disneyworld.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	public Optional<Role> findByName(String name);
}
