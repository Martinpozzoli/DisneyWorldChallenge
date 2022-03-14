package com.disneyworld.services;

import java.util.List;

import com.disneyworld.dto.GenreDTO;

public interface GenreService {

	public GenreDTO createGenre(GenreDTO denreDTO);
	
	public GenreDTO getGenreById(Long id);
	
	public List<GenreDTO> getAllGenres();
	
	public GenreDTO updateGenre(GenreDTO genreDTO, Long id);
	
	public void deleteGenre(Long id);
	
}
