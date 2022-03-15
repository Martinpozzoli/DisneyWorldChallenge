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
	
	@GetMapping()
	public List<ResponseCharacter> getAllCharacters(){
		return characterService.getAllCharacters();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CharacterDTO> getCharacterById(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(characterService.getCharacterById(id));
	}
	
	@GetMapping("/{name}")
	public List<ResponseCharacter> getCharactersByName(@PathVariable(name = "name") String name){
		return characterService.getCharactersByName(name);
	}
	
	@GetMapping("/{age}")
	public List<ResponseCharacter> getCharactersByAge(@PathVariable(name = "age") int age){
		return characterService.getCharactersByAge(age);
	}
	
	@GetMapping("/{weight}")
	public List<ResponseCharacter> getCharactersByWeight(@PathVariable(name = "weight") double weight){
		return characterService.getCharactersByWeight(weight);
	}
	
	@GetMapping("/{movies}")
	public List<ResponseCharacter> getCharactersByMediaId(@PathVariable(name = "movies") Long mediaId){
		return characterService.getCharactersByMediaId(mediaId);
	}
	
	@PostMapping
	public ResponseEntity<CharacterDTO> saveCharacter(@Valid @RequestBody CharacterDTO characterDTO){
		return new ResponseEntity<>(characterService.createCharacter(characterDTO), HttpStatus.CREATED);
	}
	
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

	@PutMapping("/{id}")
	public ResponseEntity<CharacterDTO> updateCharacter(@Valid @RequestBody CharacterDTO characterDTO, @PathVariable(name = "id") Long id){
		CharacterDTO responseCharacter = characterService.updateCharacter(characterDTO, id);
		return new ResponseEntity<>(responseCharacter, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCharacter(@PathVariable(name = "id") Long id){
		characterService.deleteCharacter(id);
		return new ResponseEntity<>("Character successfully deleted", HttpStatus.OK);
	}
	
	
}
