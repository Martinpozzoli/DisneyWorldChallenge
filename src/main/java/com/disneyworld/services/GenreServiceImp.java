package com.disneyworld.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disneyworld.dto.GenreDTO;
import com.disneyworld.dto.ResponseGenre;
import com.disneyworld.entities.Genre;
import com.disneyworld.exceptions.ResourceNotFoundException;
import com.disneyworld.repositories.GenreRepository;

@Service
public class GenreServiceImp implements GenreService{
	
	@Autowired
	private GenreRepository genreRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public GenreDTO createGenre(GenreDTO genreDTO) {
		Genre genre = mapEntity(genreDTO);
		Genre newGenre = genreRepo.save(genre);
		GenreDTO responseGenre = mapDTO(newGenre);
		
		return responseGenre;
	}

	@Override
	public GenreDTO getGenreById(Long id) {
		Genre genre = genreRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Genre", "id", id));
		
		return mapDTO(genre);
	}

	@Override
	public List<ResponseGenre> getAllGenres() {
		List<Genre> genres = genreRepo.findAll();
		List<ResponseGenre> responseGenres = new ArrayList<ResponseGenre>();
		for(Genre genre : genres) {
			ResponseGenre responseGenre = new ResponseGenre();
			responseGenre.setId(genre.getId());
			responseGenre.setName(genre.getName());
			responseGenre.setImage(genre.getImage());
			
			responseGenres.add(responseGenre);
		}
		return responseGenres;
	}

	@Override
	public GenreDTO updateGenre(GenreDTO genreDTO, Long id) {
		Genre genre = genreRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Genre", "id", id));
		
		genre.setName(genreDTO.getName());
		genre.setImage(genreDTO.getImage());
		genre.setMedias(genreDTO.getMedias());
		
		Genre updatedGenre = genreRepo.save(genre);
		
		return mapDTO(updatedGenre);
	}

	@Override
	public void deleteGenre(Long id) {
		Genre genre = genreRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Genre", "id", id));
		
		genreRepo.delete(genre);
	}
	
	private GenreDTO mapDTO(Genre genre) {
		GenreDTO genreDTO = modelMapper.map(genre, GenreDTO.class);
		return genreDTO;
	}
	
	private Genre mapEntity(GenreDTO genreDTO) {
		Genre genre = modelMapper.map(genreDTO,  Genre.class);
		return genre;
	}

}
