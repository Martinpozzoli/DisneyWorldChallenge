package com.disneyworld.services;

import java.util.List;

import com.disneyworld.dto.MediaDTO;


public interface MediaService {
	
	public MediaDTO createMedia(MediaDTO mediaDTO);
	
	public MediaDTO getMediaById(Long id);
	
	public List<MediaDTO> getAllMedia();
	
	public MediaDTO updateMedia(MediaDTO mediaDTO, Long id);

	public void deleteMedia(Long id);
	
}
