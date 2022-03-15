package com.disneyworld.services;

import java.util.List;

import com.disneyworld.dto.CharacterDTO;
import com.disneyworld.dto.ResponseCharacter;

public interface CharacterService {

	public CharacterDTO createCharacter(CharacterDTO characterDTO);
	
	public CharacterDTO getCharacterById(Long id);
	
	public List<ResponseCharacter> getAllCharacters();
	
	public CharacterDTO updateCharacter(CharacterDTO characterDTO, Long id);
	
	public void deleteCharacter(Long id);
	
	//EJERCICIO 6:
	
	public List<ResponseCharacter> getCharactersByName(String value);
	
	public List<ResponseCharacter> getCharactersByAge(int value);
	
	public List<ResponseCharacter> getCharactersByMediaId(Long value);
	
	public List<ResponseCharacter> getCharactersByWeight(double value);
}
