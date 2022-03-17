package com.disneyworld.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.disneyworld.entities.Media;


public interface MediaRepository extends JpaRepository<Media, Long>{

	@Query("SELECT m FROM Media m WHERE m.title LIKE CONCAT('%',:title,'%')")
	public List<Media> getListByTitle(@Param("title") String title);
	
	@Query("SELECT m FROM Genre g INNER JOIN g.medias m WHERE g.id =:id")
	public List<Media> getListByGenre(@Param("id") Long id);
	/*
	@Query("SELECT m FROM Media m ORDER BY m.releasedate ASC")
	public List<Media> getAllByReleaseDateASC(@Param("title") String title);
	*/
	//@Query("SELECT m FROM Media m ORDER BY m.releasedate DESC")
	public List<Media> findAllByOrderByReleaseDateDesc();
	
	public List<Media> findAllByOrderByReleaseDateAsc();
}
