package com.disneyworld.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disneyworld.entities.Character;
import com.disneyworld.entities.Media;
import com.disneyworld.exceptions.ResourceNotFoundException;
import com.disneyworld.dto.CharacterDTO;
import com.disneyworld.dto.ResponseCharacter;
import com.disneyworld.repositories.CharacterRepository;
import com.disneyworld.repositories.MediaRepository;

@Service
public class CharacterServiceImp implements CharacterService{

	@Autowired
	private CharacterRepository characterRepo;
	
	@Autowired
	private MediaRepository mediaRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CharacterDTO createCharacter(CharacterDTO characterDTO) {
		
		Character character = mapEntity(characterDTO);
		Character newCharacter = characterRepo.save(character);
		CharacterDTO responseCharacter = mapDTO(newCharacter);
		
		return responseCharacter;
	}

	@Override
	public CharacterDTO getCharacterById(Long id) {
		Character character = characterRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Character", "id", id));
		
		return mapDTO(character);
	}

	@Override
	public List<ResponseCharacter> getAllCharacters() {
		List<Character> characters = characterRepo.findAll();
		List<ResponseCharacter> responseCharacters = new ArrayList<ResponseCharacter>();
		for (Character character : characters) {
			ResponseCharacter responseCharacter = new ResponseCharacter();
			responseCharacter.setId(character.getId());
			responseCharacter.setName(character.getName());
			responseCharacter.setImage(character.getImage());
			
			responseCharacters.add(responseCharacter);
		}
		return responseCharacters;
	}

	@Override
	public CharacterDTO updateCharacter(CharacterDTO characterDTO, Long id) {
		Character character = characterRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Character", "id", id));
		
		character.setName(characterDTO.getName());
		character.setAge(characterDTO.getAge());
		character.setHistory(characterDTO.getHistory());
		character.setMedias(characterDTO.getMedias());
		character.setWeight(characterDTO.getWeight());
		
		character.setImage(characterDTO.getImage());
		
		Character updatedCharacter = characterRepo.save(character);
		
		return mapDTO(updatedCharacter);
	}

	@Override
	public void deleteCharacter(Long id) {
		Character character = characterRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Character", "id", id));
		
		characterRepo.delete(character);
	}
	
	// Converts Entity into DTO
	private CharacterDTO mapDTO(Character character) {
		CharacterDTO characterDTO = modelMapper.map(character, CharacterDTO.class);
		
		return characterDTO;
	}
	
	// Converts DTO into Entity
	private Character mapEntity(CharacterDTO characterDTO) {
		Character character = modelMapper.map(characterDTO, Character.class);
		
		return character;
	}

	//EJERCICIO 6: ----------------------------------------------
	
	@Override
	public List<ResponseCharacter> getCharactersByName(String value) {
		List<Character> characters = characterRepo.getListByName(value);
		List<ResponseCharacter> responseCharacters = new ArrayList<ResponseCharacter>();
		for (Character character : characters) {
			ResponseCharacter responseCharacter = new ResponseCharacter();
			responseCharacter.setId(character.getId());
			responseCharacter.setName(character.getName());
			responseCharacter.setImage(character.getImage());
			
			responseCharacters.add(responseCharacter);
		}
		return responseCharacters;
	}

	@Override
	public List<ResponseCharacter> getCharactersByAge(int value) {
		List<Character> characters = characterRepo.getListByAge(value);
		List<ResponseCharacter> responseCharacters = new ArrayList<ResponseCharacter>();
		for (Character character : characters) {
			ResponseCharacter responseCharacter = new ResponseCharacter();
			responseCharacter.setId(character.getId());
			responseCharacter.setName(character.getName());
			responseCharacter.setImage(character.getImage());
			
			responseCharacters.add(responseCharacter);
		}
		return responseCharacters;
	}

	@Override
	public List<ResponseCharacter> getCharactersByMediaId(Long value) {
		List<Character> characters = characterRepo.getListByMediaId(value);
		List<ResponseCharacter> responseCharacters = new ArrayList<ResponseCharacter>();
		for (Character character : characters) {
			ResponseCharacter responseCharacter = new ResponseCharacter();
			responseCharacter.setId(character.getId());
			responseCharacter.setName(character.getName());
			responseCharacter.setImage(character.getImage());
			
			responseCharacters.add(responseCharacter);
		}
		return responseCharacters;
	}

	@Override
	public List<ResponseCharacter> getCharactersByWeight(double value) {
		List<Character> characters = characterRepo.getListByWeight(value);
		List<ResponseCharacter> responseCharacters = new ArrayList<ResponseCharacter>();
		for (Character character : characters) {
			ResponseCharacter responseCharacter = new ResponseCharacter();
			responseCharacter.setId(character.getId());
			responseCharacter.setName(character.getName());
			responseCharacter.setImage(character.getImage());
			
			responseCharacters.add(responseCharacter);
		}
		return responseCharacters;
	}
	
	@Override
	public CharacterDTO updateMediaCharacter(Long mediaId, Long characterId) {
		Media media = mediaRepo.findById(mediaId).orElseThrow(() -> new ResourceNotFoundException("Media", "id", mediaId));
		Character character = characterRepo.findById(characterId).orElseThrow(() -> new ResourceNotFoundException("Character", "id", characterId));
		
		Set<Media> medias = character.getMedias();
		medias.add(media);
		character.setMedias(medias);
		
		return mapDTO(characterRepo.save(character));
	}

}
