package com.musiclibrary.song.service;

import com.musiclibrary.song.dto.*;
import com.musiclibrary.song.entity.Song;
import com.musiclibrary.song.exception.SongNotFoundException;
import com.musiclibrary.song.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    // USER → visible songs only
    public List<SongDTO> getAllSongs() {

        return songRepository.findByVisibleTrue()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ADMIN → all songs
    public List<SongDTO> getAllSongsAdmin() {

        return songRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get by ID
    public SongDTO getSongById(Long id) {

        Song song = songRepository.findById(id)
                .orElseThrow(() ->
                        new SongNotFoundException(
                                "Song not found with id: " + id
                        ));

        return mapToDTO(song);
    }

    // USER SEARCH
    public List<SongDTO> searchSongs(String q) {

        return songRepository.searchAll(q)
                .stream()
                .filter(Song::getVisible)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ADMIN SEARCH (includes hidden)
    public List<SongDTO> adminSearchSongs(
            String q) {

        String keyword =
                q.toLowerCase().trim();

        return songRepository.findAll()
                .stream()
                .filter(song ->

                        (song.getTitle() != null &&
                                song.getTitle()
                                        .toLowerCase()
                                        .contains(keyword))

                                ||

                                (song.getArtist() != null &&
                                        song.getArtist()
                                                .toLowerCase()
                                                .contains(keyword))

                                ||

                                (song.getAlbum() != null &&
                                        song.getAlbum()
                                                .toLowerCase()
                                                .contains(keyword))

                                ||

                                (song.getMusicDirector() != null &&
                                        song.getMusicDirector()
                                                .toLowerCase()
                                                .contains(keyword))
                )
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Search field
    public List<SongDTO> searchByField(
            String field,
            String value) {

        List<Song> results;

        switch (field) {

            case "artist":
                results =
                        songRepository
                                .findByArtistContainingIgnoreCase(
                                        value
                                );
                break;

            case "album":
                results =
                        songRepository
                                .findByAlbumContainingIgnoreCase(
                                        value
                                );
                break;

            case "musicDirector":
                results =
                        songRepository
                                .findByMusicDirectorContainingIgnoreCase(
                                        value
                                );
                break;

            default:
                results =
                        songRepository.searchAll(
                                value
                        );
        }

        return results.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Create
    public SongDTO createSong(
            SongCreateRequest request) {

        Song song = Song.builder()
                .title(request.getTitle())
                .artist(request.getArtist())
                .musicDirector(
                        request.getMusicDirector()
                )
                .album(request.getAlbum())
                .releaseDate(
                        request.getReleaseDate()
                )
                .genre(request.getGenre())
                .duration(
                        request.getDuration()
                )
                .albumArt(
                        request.getAlbumArt()
                )
                .language(
                        request.getLanguage()
                )
                .visible(
                        request.getVisible() != null
                                ? request.getVisible()
                                : true
                )
                .build();

        return mapToDTO(
                songRepository.save(song)
        );
    }

    // Update
    public SongDTO updateSong(
            Long id,
            SongCreateRequest request) {

        Song song =
                songRepository.findById(id)
                        .orElseThrow(() ->
                                new SongNotFoundException(
                                        "Song not found"
                                ));

        if (request.getTitle() != null)
            song.setTitle(
                    request.getTitle()
            );

        if (request.getArtist() != null)
            song.setArtist(
                    request.getArtist()
            );

        if (request.getMusicDirector()
                != null)
            song.setMusicDirector(
                    request.getMusicDirector()
            );

        if (request.getAlbum() != null)
            song.setAlbum(
                    request.getAlbum()
            );

        if (request.getReleaseDate()
                != null)
            song.setReleaseDate(
                    request.getReleaseDate()
            );

        if (request.getGenre() != null)
            song.setGenre(
                    request.getGenre()
            );

        if (request.getDuration()
                != null)
            song.setDuration(
                    request.getDuration()
            );

        if (request.getAlbumArt()
                != null)
            song.setAlbumArt(
                    request.getAlbumArt()
            );

        if (request.getLanguage()
                != null)
            song.setLanguage(
                    request.getLanguage()
            );

        return mapToDTO(
                songRepository.save(song)
        );
    }

    // Delete
    public void deleteSong(Long id) {

        if (!songRepository.existsById(id)) {
            throw new SongNotFoundException(
                    "Song not found"
            );
        }

        songRepository.deleteById(id);
    }

    // Toggle visibility
    public SongDTO toggleVisibility(
            Long id) {

        Song song =
                songRepository.findById(id)
                        .orElseThrow(() ->
                                new SongNotFoundException(
                                        "Song not found"
                                ));

        song.setVisible(
                !song.getVisible()
        );

        return mapToDTO(
                songRepository.save(song)
        );
    }

    // DTO mapping
    private SongDTO mapToDTO(
            Song song) {

        SongDTO dto =
                new SongDTO();

        dto.setId(song.getId());
        dto.setTitle(song.getTitle());
        dto.setArtist(song.getArtist());
        dto.setMusicDirector(
                song.getMusicDirector()
        );
        dto.setAlbum(song.getAlbum());
        dto.setReleaseDate(
                song.getReleaseDate()
        );
        dto.setGenre(song.getGenre());
        dto.setDuration(
                song.getDuration()
        );
        dto.setAlbumArt(
                song.getAlbumArt()
        );
        dto.setLanguage(
                song.getLanguage()
        );
        dto.setVisible(
                song.getVisible()
        );

        return dto;
    }
}