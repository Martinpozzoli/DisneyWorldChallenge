package com.disneyworld.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.disneyworld.entities.Image;
import com.disneyworld.entities.Media;

public class CharacterDTO {

	private long id;
	
	private Image image;
	
	@NotEmpty
	@Size(min = 2, message = "Name must be at least 2 characters long")
	private String name;
	
	@NotEmpty
	private int age;
	
	@NotEmpty
	private double weight;
	
	@NotEmpty
	@Size(min = 10, message = "Character history must be at least 10 characters long")
	private String history;
	
	private Set<Media> medias = new HashSet<>();

	public CharacterDTO(long id, Image image, String name, int age, double weight, String history,
			Set<Media> medias) {
		super();
		this.id = id;
		this.image = image;
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.history = history;
		this.medias = medias;
	}

	public CharacterDTO() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public Set<Media> getMedias() {
		return medias;
	}

	public void setMedias(Set<Media> medias) {
		this.medias = medias;
	}
	
	
}
