package com.disneyworld.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disneyworld.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{

}
