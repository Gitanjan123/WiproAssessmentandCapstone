package com.musiclibrary.song.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="songs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Song {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String title;
	private String artist;
	private String musicDirector;
	private String album;
	private LocalDate releaseDate;
	private String genre;
	private Integer duration;
	private String albumArt;
	private String language;
	@Column(nullable=false)
	private Boolean visible=true;
}
