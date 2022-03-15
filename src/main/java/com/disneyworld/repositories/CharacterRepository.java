package com.disneyworld.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.disneyworld.entities.Character;

public interface CharacterRepository extends JpaRepository<Character, Long>{

	@Query("SELECT c FROM Character c WHERE c.name LIKE CONCAT('%',:name,'%')")
	public List<Character> getListByName(@Param("name") String name);
	
	@Query("SELECT c FROM Character c WHERE c.age =:age")
	public List<Character> getListByAge(@Param("age") int age);
	
	@Query("SELECT c FROM Character c WHERE c.weight =:weight")
	public List<Character> getListByWeight(@Param("weight") double weight);
	
	@Query("SELECT c FROM Character c LEFT JOIN FETCH t.medias m WHERE m.id =:id")
	public List<Character> getListByMediaId(@Param("id") Long id);
	
}
