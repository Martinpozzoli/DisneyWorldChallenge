package com.disneyworld.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.disneyworld.dto.CharacterDTO;
import com.disneyworld.dto.ResponseCharacter;
import com.disneyworld.entities.Image;
import com.disneyworld.services.CharacterService;
import com.disneyworld.services.ImageService;

@RestController
@RequestMapping("/characters")
public class CharacterController {
	
	@Autowired
	private CharacterService characterService;
	
	@Autowired
	private ImageService imageService;
	
	@GetMapping("/{id}")
	public ResponseEntity<CharacterDTO> getCharacterById(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(characterService.getCharacterById(id));
	}
	
	@GetMapping()
	public List<ResponseCharacter> getCharacters(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) Integer age,
			@RequestParam(required = false) Double weight,
			@RequestParam(required = false) Long mediaId){
		//By Name:
		if((name != null && !name.isBlank()) && age == null && weight == null && mediaId == null) {
			return characterService.getCharactersByName(name);
		//By Age:
		}else if((name == null || name.isBlank()) && age != null && weight == null && mediaId == null) {
			return characterService.getCharactersByAge(age);
		//By Weight:
		}else if((name == null || name.isBlank()) && age == null && weight != null && mediaId == null) {
			return characterService.getCharactersByWeight(weight);
		//By Media Id:
		}else if((name == null || name.isBlank()) && age == null && weight == null && mediaId != null) {
			return characterService.getCharactersByMediaId(mediaId);
		//All Characters:
		}else {
		return characterService.getAllCharacters();
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<CharacterDTO> saveCharacter(@Valid @RequestBody CharacterDTO characterDTO){
		return new ResponseEntity<>(characterService.createCharacter(characterDTO), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/{id}/update-image")
	public ResponseEntity<CharacterDTO> saveCharacterImage(@RequestParam(required = true, value = "image") MultipartFile file, 
														   @PathVariable(name = "id") Long id){
		
		CharacterDTO characterDTO = characterService.getCharacterById(id);
		Image image;
		
		if(characterDTO.getImage() != null) {
			image = imageService.updateImage(file, characterDTO.getImage().getId());
		}else {
			image = imageService.saveImage(file);
		}
		
		characterDTO.setImage(image);
		characterService.updateCharacter(characterDTO, id);
		
		return new ResponseEntity<>(characterDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<CharacterDTO> updateCharacter(@Valid @RequestBody CharacterDTO characterDTO, @PathVariable(name = "id") Long id){
		CharacterDTO responseCharacter = characterService.updateCharacter(characterDTO, id);
		return new ResponseEntity<>(responseCharacter, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/add-media/{characterId}/{mediaId}")
	public ResponseEntity<CharacterDTO> updateCharacterMedia(
			@PathVariable(name = "characterId") Long characterId,
			@PathVariable(name = "mediaId") Long mediaId){
		CharacterDTO responseCharacter = characterService.updateMediaCharacter(mediaId, characterId);
		return new ResponseEntity<>(responseCharacter, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCharacter(@PathVariable(name = "id") Long id){
		characterService.deleteCharacter(id);
		return new ResponseEntity<>("Character successfully deleted", HttpStatus.OK);
	}
	
	
}
