package com.disneyworld.services;

import org.springframework.web.multipart.MultipartFile;

import com.disneyworld.entities.Image;

public interface ImageService {

	public Image saveImage(MultipartFile file);
	
	public Image updateImage(MultipartFile file, Long id);
	
	public Image getImageById(Long id);
	
	public void deleteImage(Long id);
}
