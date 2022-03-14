package com.disneyworld.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disneyworld.dto.MediaDTO;
import com.disneyworld.entities.Media;
import com.disneyworld.exceptions.ResourceNotFoundException;
import com.disneyworld.repositories.MediaRepository;

@Service
public class MediaServiceImp implements MediaService{

	@Autowired
	private MediaRepository mediaRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public MediaDTO createMedia(MediaDTO mediaDTO) {
		Media media = mapEntity(mediaDTO);
		Media newMedia = mediaRepo.save(media);
		return mapDTO(newMedia);
	}

	@Override
	public MediaDTO getMediaById(Long id) {
		Media media = mediaRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Media", "id", id));
		
		return mapDTO(media);
	}

	@Override
	public List<MediaDTO> getAllMedia() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediaDTO updateMedia(MediaDTO mediaDTO, Long id) {
		Media media = mediaRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Media", "id", id));
		
		media.setTitle(mediaDTO.getTitle());
		media.setReleaseDate(mediaDTO.getReleaseDate());
		media.setRating(mediaDTO.getRating());
		media.setCharacters(mediaDTO.getCharacters());
		
		media.setImage(mediaDTO.getImage());
		
		Media updatedMedia = mediaRepo.save(media);
		
		return mapDTO(updatedMedia);
	}

	@Override
	public void deleteMedia(Long id) {
		Media media = mediaRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Media", "id", id));

		mediaRepo.delete(media);
	}
	
	private MediaDTO mapDTO(Media media) {
		MediaDTO mediaDTO = modelMapper.map(media, MediaDTO.class);
		return mediaDTO;
	}

	private Media mapEntity(MediaDTO mediaDTO) {
		Media media = modelMapper.map(mediaDTO, Media.class);
		return media;
	}
}
