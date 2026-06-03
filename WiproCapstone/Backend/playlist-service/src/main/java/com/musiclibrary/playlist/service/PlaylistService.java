package com.musiclibrary.playlist.service;

import com.musiclibrary.playlist.dto.*;
import com.musiclibrary.playlist.entity.*;
import com.musiclibrary.playlist.exception.PlaylistNotFoundException;
import com.musiclibrary.playlist.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistSongRepository playlistSongRepository;

    @Autowired
    private RestTemplate restTemplate;

    // ✅ Direct URL instead of service-discovery name
    private static final String SONG_SERVICE_URL = 
        "http://localhost:8083";

    // Get all playlists for a user
    public List<PlaylistDTO> getUserPlaylists(Long userId) {
        return playlistRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get playlist by ID with songs
    public PlaylistDTO getPlaylistById(Long id) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() ->
                    new PlaylistNotFoundException(
                        "Playlist not found: " + id));

        PlaylistDTO dto = mapToDTO(playlist);

        List<PlaylistSong> playlistSongs =
            playlistSongRepository.findByPlaylistId(id);

        List<SongDTO> songs = playlistSongs.stream()
                .map(ps -> getSongFromSongService(ps.getSongId()))
                .filter(s -> s != null)
                .collect(Collectors.toList());

        dto.setSongs(songs);
        dto.setSongCount(songs.size());
        return dto;
    }

    // Create playlist
    public PlaylistDTO createPlaylist(
            PlaylistCreateRequest request, Long userId) {
        Playlist playlist = Playlist.builder()
                .name(request.getName())
                .description(request.getDescription())
                .userId(userId)
                .build();
        return mapToDTO(playlistRepository.save(playlist));
    }

    // Update playlist
    public PlaylistDTO updatePlaylist(
            Long id, PlaylistCreateRequest request, Long userId) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() ->
                    new PlaylistNotFoundException(
                        "Playlist not found: " + id));

        if (!playlist.getUserId().equals(userId)) {
            throw new RuntimeException(
                "Not authorized to update this playlist");
        }

        playlist.setName(request.getName());
        if (request.getDescription() != null)
            playlist.setDescription(request.getDescription());

        return mapToDTO(playlistRepository.save(playlist));
    }

    // Delete playlist
    @Transactional
    public void deletePlaylist(Long id, Long userId) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() ->
                    new PlaylistNotFoundException(
                        "Playlist not found: " + id));

        if (!playlist.getUserId().equals(userId)) {
            throw new RuntimeException(
                "Not authorized to delete this playlist");
        }

        playlistSongRepository
            .findByPlaylistId(id)
            .forEach(ps -> playlistSongRepository.delete(ps));

        playlistRepository.deleteById(id);
    }

    // Add song to playlist
    public PlaylistDTO addSongToPlaylist(
            Long playlistId, Long songId, Long userId) {
        Playlist playlist =
            playlistRepository.findById(playlistId)
                .orElseThrow(() ->
                    new PlaylistNotFoundException(
                        "Playlist not found: " + playlistId));

        if (!playlist.getUserId().equals(userId)) {
            throw new RuntimeException("Not authorized");
        }

        if (playlistSongRepository
                .existsByPlaylistIdAndSongId(playlistId, songId)) {
            throw new RuntimeException("Song already in playlist");
        }

        PlaylistSong ps = new PlaylistSong();
        ps.setPlaylistId(playlistId);
        ps.setSongId(songId);
        playlistSongRepository.save(ps);

        return getPlaylistById(playlistId);
    }

    // Remove song from playlist
    @Transactional
    public PlaylistDTO removeSongFromPlaylist(
            Long playlistId, Long songId, Long userId) {
        Playlist playlist =
            playlistRepository.findById(playlistId)
                .orElseThrow(() ->
                    new PlaylistNotFoundException(
                        "Playlist not found: " + playlistId));

        if (!playlist.getUserId().equals(userId)) {
            throw new RuntimeException("Not authorized");
        }

        playlistSongRepository
            .deleteByPlaylistIdAndSongId(playlistId, songId);

        return getPlaylistById(playlistId);
    }

    // Search songs in playlist
    public List<SongDTO> searchSongsInPlaylist(
            Long playlistId, String query) {
        List<PlaylistSong> playlistSongs =
            playlistSongRepository.findByPlaylistId(playlistId);

        return playlistSongs.stream()
                .map(ps -> getSongFromSongService(ps.getSongId()))
                .filter(s -> s != null &&
                    (s.getTitle().toLowerCase()
                        .contains(query.toLowerCase()) ||
                     s.getArtist().toLowerCase()
                        .contains(query.toLowerCase())))
                .collect(Collectors.toList());
    }

    // ✅ Now calls localhost:8083 directly — no token needed
    // because song service now permitAll() on GET /api/songs/**
    private SongDTO getSongFromSongService(Long songId) {
        try {
            return restTemplate.getForObject(
                SONG_SERVICE_URL + "/api/songs/" + songId,
                SongDTO.class);
        } catch (Exception e) {
            System.err.println("Failed to fetch song " 
                + songId + ": " + e.getMessage());
            return null;
        }
    }

    private PlaylistDTO mapToDTO(Playlist playlist) {
        PlaylistDTO dto = new PlaylistDTO();
        dto.setId(playlist.getId());
        dto.setName(playlist.getName());
        dto.setDescription(playlist.getDescription());
        dto.setUserId(playlist.getUserId());

        int count = playlistSongRepository
            .findByPlaylistId(playlist.getId()).size();
        dto.setSongCount(count);

        return dto;
    }
}