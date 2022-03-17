package com.disneyworld.dto;

import java.time.LocalDate;

import com.disneyworld.entities.Image;

public class ResponseMedia {

	private Long id;
	private String title;
	private Image image;
	private LocalDate releaseDate;
	
	public ResponseMedia() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	
}
