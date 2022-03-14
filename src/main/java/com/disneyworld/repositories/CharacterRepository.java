package com.disneyworld.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disneyworld.entities.Character;

public interface CharacterRepository extends JpaRepository<Character, Long>{

}
