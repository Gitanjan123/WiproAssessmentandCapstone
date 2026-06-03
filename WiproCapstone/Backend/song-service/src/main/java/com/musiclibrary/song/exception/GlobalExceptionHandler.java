package com.musiclibrary.song.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(SongNotFoundException.class)
	public ResponseEntity<?>handleNotFound(SongNotFoundException ex)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
	}
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?>handleRuntime(RuntimeException ex)
	{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?>handleGeneral(Exception ex)
	{
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", ex.getMessage()));
	}
}
