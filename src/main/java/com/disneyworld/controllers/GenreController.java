package com.disneyworld.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.disneyworld.dto.GenreDTO;
import com.disneyworld.dto.ResponseGenre;
import com.disneyworld.entities.Image;
import com.disneyworld.services.GenreService;
import com.disneyworld.services.ImageService;

@RestController
@RequestMapping("/genres")
public class GenreController {

	@Autowired
	private GenreService genreService;
	
	@Autowired
	private ImageService imageService;
	
	@GetMapping()
	public List<ResponseGenre> getAllGenres(){
		return genreService.getAllGenres();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GenreDTO> getGenreById(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(genreService.getGenreById(id));
	}
	
	@PostMapping
	public ResponseEntity<GenreDTO> saveGenre(@Valid @RequestBody GenreDTO genreDTO){
		return new ResponseEntity<>(genreService.createGenre(genreDTO), HttpStatus.CREATED);
	}
	
	@PostMapping("/{id}/update-image")
	public ResponseEntity<GenreDTO> saveGenreImage(@RequestParam(required = true, value = "image") MultipartFile file,
												   @PathVariable(name = "id") Long id){
		
		GenreDTO genreDTO = genreService.getGenreById(id);
		Image image;
		
		if(genreDTO.getImage() != null) {
			image = imageService.updateImage(file, genreDTO.getImage().getId());
		}else {
			image = imageService.saveImage(file);
		}
		
		genreDTO.setImage(image);
		genreService.updateGenre(genreDTO, id);
		
		return new ResponseEntity<>(genreDTO, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<GenreDTO> updateGenre(@Valid @RequestBody GenreDTO genreDTO, @PathVariable(name = "id") Long id){
		GenreDTO responseGenre = genreService.updateGenre(genreDTO, id);
		return new ResponseEntity<>(responseGenre, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteGenre(@PathVariable(name = "id") Long id){
		genreService.deleteGenre(id);
		return new ResponseEntity<>("Genre successfully deleted", HttpStatus.OK);
	}
	
}
