package com.disneyworld.services;

import java.util.List;

import com.disneyworld.dto.GenreDTO;
import com.disneyworld.dto.ResponseGenre;

public interface GenreService {

	public GenreDTO createGenre(GenreDTO denreDTO);
	
	public GenreDTO getGenreById(Long id);
	
	public List<ResponseGenre> getAllGenres();
	
	public GenreDTO updateGenre(GenreDTO genreDTO, Long id);
	
	public void deleteGenre(Long id);
	
}
