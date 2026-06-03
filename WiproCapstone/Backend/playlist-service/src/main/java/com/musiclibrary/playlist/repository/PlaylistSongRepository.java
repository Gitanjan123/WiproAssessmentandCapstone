package com.musiclibrary.playlist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musiclibrary.playlist.entity.PlaylistSong;

public interface PlaylistSongRepository extends JpaRepository<PlaylistSong,Long> {
	List<PlaylistSong>findByPlaylistId(Long playlistId);
	boolean existsByPlaylistIdAndSongId(Long playlistId,Long songId);
	
	void deleteByPlaylistIdAndSongId(Long playlistId,Long songId);
}
