package com.disneyworld.controllers;

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

import com.disneyworld.dto.MediaDTO;
import com.disneyworld.entities.Image;
import com.disneyworld.services.ImageService;
import com.disneyworld.services.MediaService;

@RestController
@RequestMapping("/movies")
public class MediaController {

	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private ImageService imageService;
	
	@GetMapping("/{id}")
	public ResponseEntity<MediaDTO> getMediaById(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(mediaService.getMediaById(id));
	}
	
	@PostMapping
	public ResponseEntity<MediaDTO> saveMedia(@Valid @RequestBody MediaDTO mediaDTO){
		return new ResponseEntity<>(mediaService.createMedia(mediaDTO), HttpStatus.CREATED);
	}
	
	@PostMapping("/{id}/update-image")
	public ResponseEntity<MediaDTO> saveMediaImage(@RequestParam(required = true, value = "image") MultipartFile file,
												   @PathVariable(name = "id") Long id){
													
		MediaDTO mediaDTO = mediaService.getMediaById(id);
		Image image;
		
		if(mediaDTO.getImage() != null) {
			image = imageService.updateImage(file, mediaDTO.getImage().getId());
		}else {
			image = imageService.saveImage(file);
		}
		
		mediaDTO.setImage(image);
		mediaService.updateMedia(mediaDTO, id);
		
		return new ResponseEntity<>(mediaDTO, HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MediaDTO> updateMedia(@Valid @RequestBody MediaDTO mediaDTO, @PathVariable(name = "id") Long id){
		MediaDTO responseMedia = mediaService.updateMedia(mediaDTO, id);
		return new ResponseEntity<>(responseMedia, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteMedia(@PathVariable(name = "id") Long id){
		mediaService.deleteMedia(id);
		return new ResponseEntity<>("Media successfully deleted", HttpStatus.OK);
	}
	
}
