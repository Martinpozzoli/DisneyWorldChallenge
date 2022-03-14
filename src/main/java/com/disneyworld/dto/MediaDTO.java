package com.disneyworld.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.disneyworld.entities.Character;
import com.disneyworld.entities.Image;

public class MediaDTO {
	
	private long id;
	
	private Image image;
	
	@NotEmpty
	@Size(min = 2, message = "Title must be at least 2 characters long")
	private String title;
	
	@NotEmpty
	private Date releaseDate;
	
	@NotEmpty
	@Min(1)
	@Max(5)
	private int rating;
	
	private Set<Character> characters = new HashSet<>();
	
	
	public MediaDTO(long id, Image image, String title, Date releaseDate, int rating, Set<Character> characters) {
		super();
		this.id = id;
		this.image = image;
		this.title = title;
		this.releaseDate = releaseDate;
		this.rating = rating;
		this.characters = characters;
	}

	public MediaDTO() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Set<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(Set<Character> characters) {
		this.characters = characters;
	}
}
