package com.disneyworld.services;

import java.util.List;

import com.disneyworld.dto.MediaDTO;
import com.disneyworld.dto.ResponseMedia;


public interface MediaService {
	
	public MediaDTO createMedia(MediaDTO mediaDTO);
	
	public MediaDTO getMediaById(Long id);
	
	public List<ResponseMedia> getAllMedia();
	
	public MediaDTO updateMedia(MediaDTO mediaDTO, Long id);

	public void deleteMedia(Long id);
	
	//EJERCICIO 6: -----------------------
	
	public List<ResponseMedia> getMediaByTitle(String value);
	
	public List<ResponseMedia> getMediaByGenre(Long value);
	
	public List<ResponseMedia> getMediasByReleaseDate(String value);
	
}
