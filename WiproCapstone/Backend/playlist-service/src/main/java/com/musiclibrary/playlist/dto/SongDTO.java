package com.musiclibrary.playlist.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SongDTO {
	private Long id;
	private String title;
	private String artist;
	private String musicDirector;
	private String album;
	private LocalDate releaseDate;
	private String genre;
	private Integer duration;
	private String albumArt;
	private String language;
	private Boolean visible;
}
