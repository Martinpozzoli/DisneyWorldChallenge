package com.disneyworld.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.disneyworld.dto.MediaDTO;
import com.disneyworld.dto.ResponseMedia;
import com.disneyworld.entities.Media;
import com.disneyworld.exceptions.DisneyAppException;
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
	public List<ResponseMedia> getAllMedia() {
		List<Media> medias = mediaRepo.findAll();
		List<ResponseMedia> responseMedias = new ArrayList<ResponseMedia>();
		for(Media media : medias) {
			ResponseMedia responseMedia = new ResponseMedia();
			responseMedia.setId(media.getId());
			responseMedia.setTitle(media.getTitle());
			responseMedia.setImage(media.getImage());
			responseMedia.setReleaseDate(media.getReleaseDate());
			
			responseMedias.add(responseMedia);
		}
		return responseMedias;
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

	//EJERCICIO 6-------------------------
	
	@Override
	public List<ResponseMedia> getMediaByTitle(String value) {
		List<Media> medias = mediaRepo.getListByTitle(value);
		List<ResponseMedia> responseMedias = new ArrayList<ResponseMedia>();
		for(Media media : medias) {
			ResponseMedia responseMedia = new ResponseMedia();
			responseMedia.setId(media.getId());
			responseMedia.setTitle(media.getTitle());
			responseMedia.setImage(media.getImage());
			responseMedia.setReleaseDate(media.getReleaseDate());
			
			responseMedias.add(responseMedia);
		}
		return responseMedias;
	}

	@Override
	public List<ResponseMedia> getMediaByGenre(Long value) {
		List<Media> medias = mediaRepo.getListByGenre(value);
		List<ResponseMedia> responseMedias = new ArrayList<ResponseMedia>();
		for(Media media : medias) {
			ResponseMedia responseMedia = new ResponseMedia();
			responseMedia.setId(media.getId());
			responseMedia.setTitle(media.getTitle());
			responseMedia.setImage(media.getImage());
			responseMedia.setReleaseDate(media.getReleaseDate());
			
			responseMedias.add(responseMedia);
		}
		return responseMedias;
	}

	@Override
	public List<ResponseMedia> getMediasByReleaseDate(String value) {
		List<Media> medias = new ArrayList<Media>();
		if(value.equalsIgnoreCase("asc")) {
			medias = mediaRepo.findAllByOrderByReleaseDateAsc();
		}else if(value.equalsIgnoreCase("desc")) {
			medias = mediaRepo.findAllByOrderByReleaseDateDesc();
		}else {
			throw new DisneyAppException(HttpStatus.BAD_REQUEST, "Invalid sort method... try with ASC or DESC.");
		}
		List<ResponseMedia> responseMedias = new ArrayList<ResponseMedia>();
		for(Media media : medias) {
			ResponseMedia responseMedia = new ResponseMedia();
			responseMedia.setId(media.getId());
			responseMedia.setTitle(media.getTitle());
			responseMedia.setImage(media.getImage());
			responseMedia.setReleaseDate(media.getReleaseDate());
			
			responseMedias.add(responseMedia);
		}
		return responseMedias;
	}
}
