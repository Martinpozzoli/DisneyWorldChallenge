package com.disneyworld.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disneyworld.entities.Character;
import com.disneyworld.exceptions.ResourceNotFoundException;
import com.disneyworld.dto.CharacterDTO;
import com.disneyworld.repositories.CharacterRepository;

@Service
public class CharacterServiceImp implements CharacterService{

	@Autowired
	private CharacterRepository characterRepo;
	
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
	public List<CharacterDTO> getAllCharacters() {
		// TODO Auto-generated method stub
		return null;
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

}
