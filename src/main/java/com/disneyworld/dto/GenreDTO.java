package com.disneyworld.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.disneyworld.entities.Image;
import com.disneyworld.entities.Media;

public class GenreDTO {

	private long id;
	
	@NotEmpty
	@Size(min = 2, message = "Genre name must be at least 2 characters long")
	private String name;
	
	private Image image;

	private Set<Media> medias = new HashSet<>();

	public GenreDTO(long id, String name, Image image, Set<Media> medias) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.medias = medias;
	}

	public GenreDTO() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Set<Media> getMedias() {
		return medias;
	}

	public void setMedias(Set<Media> medias) {
		this.medias = medias;
	}
	
	
}
