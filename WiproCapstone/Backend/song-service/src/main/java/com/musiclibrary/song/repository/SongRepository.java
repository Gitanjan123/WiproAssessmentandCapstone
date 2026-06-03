package com.musiclibrary.song.repository;

import com.musiclibrary.song.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface SongRepository
        extends JpaRepository<Song, Long> {

    // Search by all fields
    @Query("SELECT s FROM Song s WHERE " +
           "LOWER(s.title) LIKE LOWER(CONCAT('%',:q,'%')) OR " +
           "LOWER(s.artist) LIKE LOWER(CONCAT('%',:q,'%')) OR " +
           "LOWER(s.album) LIKE LOWER(CONCAT('%',:q,'%')) OR " +
           "LOWER(s.musicDirector) LIKE LOWER(CONCAT('%',:q,'%'))")
    List<Song> searchAll(@Param("q") String query);

    // Search by artist
    List<Song> findByArtistContainingIgnoreCase(String artist);

    // Search by album
    List<Song> findByAlbumContainingIgnoreCase(String album);

    // Search by music director
    List<Song> findByMusicDirectorContainingIgnoreCase(
        String musicDirector);

    // Get only visible songs
    List<Song> findByVisibleTrue();
}