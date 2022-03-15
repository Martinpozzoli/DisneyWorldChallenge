package com.disneyworld.dto;

import com.disneyworld.entities.Image;

public class ResponseCharacter {

	private Long id;
	private String name;
	private Image image;
	
	public ResponseCharacter() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
}
