package com.musiclibrary.song.exception;

public class SongNotFoundException extends RuntimeException {
	public SongNotFoundException(String message)
	{
		super(message);
	}
}
