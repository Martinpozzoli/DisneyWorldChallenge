package com.disneyworld.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disneyworld.entities.Media;


public interface MediaRepository extends JpaRepository<Media, Long>{

}
