package com.disneyworld.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disneyworld.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

}
