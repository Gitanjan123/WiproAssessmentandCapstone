package com.musiclibrary.playlist.controller;

import com.musiclibrary.playlist.dto.*;
import com.musiclibrary.playlist.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    // Get all playlists for logged in user
    @GetMapping("/api/playlists")
    public ResponseEntity<?> getUserPlaylists(
            @RequestParam Long userId) {
        return ResponseEntity.ok(
            playlistService.getUserPlaylists(userId));
    }

    // Get playlist by ID
    @GetMapping("/api/playlists/{id}")
    public ResponseEntity<?> getPlaylistById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
            playlistService.getPlaylistById(id));
    }

    // Create playlist
    @PostMapping("/api/playlists")
    public ResponseEntity<?> createPlaylist(
            @RequestBody PlaylistCreateRequest request,
            @RequestParam Long userId) {
        return ResponseEntity.ok(
            playlistService.createPlaylist(
                request, userId));
    }

    // Update playlist
    @PutMapping("/api/playlists/{id}")
    public ResponseEntity<?> updatePlaylist(
            @PathVariable Long id,
            @RequestBody PlaylistCreateRequest request,
            @RequestParam Long userId) {
        return ResponseEntity.ok(
            playlistService.updatePlaylist(
                id, request, userId));
    }

    // Delete playlist
    @DeleteMapping("/api/playlists/{id}")
    public ResponseEntity<?> deletePlaylist(
            @PathVariable Long id,
            @RequestParam Long userId) {
        playlistService.deletePlaylist(id, userId);
        return ResponseEntity.ok(
            Map.of("message",
                "Playlist deleted successfully"));
    }

    // Add song to playlist
    @PostMapping("/api/playlists/{id}/songs")
    public ResponseEntity<?> addSong(
            @PathVariable Long id,
            @RequestBody AddSongRequest request,
            @RequestParam Long userId) {
        return ResponseEntity.ok(
            playlistService.addSongToPlaylist(
                id, request.getSongId(), userId));
    }

    // Remove song from playlist
    @DeleteMapping(
        "/api/playlists/{id}/songs/{songId}")
    public ResponseEntity<?> removeSong(
            @PathVariable Long id,
            @PathVariable Long songId,
            @RequestParam Long userId) {
        return ResponseEntity.ok(
            playlistService.removeSongFromPlaylist(
                id, songId, userId));
    }

    // Search songs in playlist
    @GetMapping(
        "/api/playlists/{id}/songs/search")
    public ResponseEntity<?> searchSongs(
            @PathVariable Long id,
            @RequestParam String q) {
        return ResponseEntity.ok(
            playlistService.searchSongsInPlaylist(
                id, q));
    }
}