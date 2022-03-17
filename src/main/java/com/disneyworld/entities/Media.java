package com.disneyworld.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="media",uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Media {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Image image;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "release_date")
	private LocalDate releaseDate;
	
	@Column(name = "rating")
	private int rating;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "media_characters", joinColumns = @JoinColumn(name = "media_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "character_id", referencedColumnName = "id"))
	@JsonIgnoreProperties("medias")
	private Set<Character> characters = new HashSet<>();
	
	
	public Media(long id, Image image, String title, LocalDate releaseDate, int rating, Set<Character> characters) {
		super();
		this.id = id;
		this.image = image;
		this.title = title;
		this.releaseDate = releaseDate;
		this.rating = rating;
		this.characters = characters;
	}

	public Media() {
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

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
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
