package com.musiclibrary.playlist.dto;

import lombok.Data;

@Data
public class PlaylistCreateRequest {
	private String name;
	private String description;
}
