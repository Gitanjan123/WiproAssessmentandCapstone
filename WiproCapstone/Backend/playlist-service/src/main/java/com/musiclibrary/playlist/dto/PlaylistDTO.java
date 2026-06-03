package com.musiclibrary.playlist.dto;

import java.util.List;

import lombok.Data;

@Data
public class PlaylistDTO {
	private Long id;
	private String name;
	private String description;
	private Long userId;
	private Integer songCount;
	private List<SongDTO>songs;
}	
