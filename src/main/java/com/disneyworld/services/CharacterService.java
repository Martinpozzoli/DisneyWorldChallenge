package com.disneyworld.services;

import java.util.List;

import com.disneyworld.dto.CharacterDTO;

public interface CharacterService {

	public CharacterDTO createCharacter(CharacterDTO characterDTO);
	
	public CharacterDTO getCharacterById(Long id);
	
	public List<CharacterDTO> getAllCharacters();
	
	public CharacterDTO updateCharacter(CharacterDTO characterDTO, Long id);
	
	public void deleteCharacter(Long id);
}
