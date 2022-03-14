package com.disneyworld.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.disneyworld.entities.Image;
import com.disneyworld.exceptions.ResourceNotFoundException;
import com.disneyworld.repositories.ImageRepository;

@Service
public class ImageServiceImp implements ImageService{

	@Autowired
	private ImageRepository imageRepo;

	@Override
	public Image saveImage(MultipartFile file) {
		if(file != null) {
			try {
				Image image = new Image();
				image.setMime(file.getContentType());
				image.setName(file.getName());
				image.setContent(file.getBytes());
				
				return imageRepo.save(image);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Image updateImage(MultipartFile file, Long id) {
		if(file != null) {
			try {
			Image image = imageRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image", "id", id));
			
			image.setMime(file.getContentType());
			image.setName(file.getName());
			image.setContent(file.getBytes());
			
			return imageRepo.save(image);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Image getImageById(Long id) {
		Image image = imageRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image", "id", id));
		
		return image;
	}

	@Override
	public void deleteImage(Long id) {
		Image image = imageRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image", "id", id));
		
		imageRepo.delete(image);
	}
	
	
}
