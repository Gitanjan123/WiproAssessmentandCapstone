package com.musiclibrary.song.controller;

import com.musiclibrary.song.dto.SongCreateRequest;
import com.musiclibrary.song.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class SongController {

    @Autowired
    private SongService songService;

    // USER → only visible songs
    @GetMapping("/api/songs")
    public ResponseEntity<?> getAllSongs() {
        return ResponseEntity.ok(
            songService.getAllSongs());
    }

    // ADMIN → ALL songs including hidden
    @GetMapping("/api/admin/songs")  // ← FIXED: added /api prefix
    public ResponseEntity<?> getAllSongsAdmin() {
        return ResponseEntity.ok(
            songService.getAllSongsAdmin());
    }

    // Get by ID
    @GetMapping("/api/songs/{id}")
    public ResponseEntity<?> getSongById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
            songService.getSongById(id));
    }

    // USER SEARCH — visible only
    @GetMapping("/api/songs/search")
    public ResponseEntity<?> searchSongs(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String artist,
            @RequestParam(required = false) String album,
            @RequestParam(required = false) String musicDirector) {

        if (q != null && !q.isBlank())
            return ResponseEntity.ok(
                songService.searchSongs(q));
        if (artist != null)
            return ResponseEntity.ok(
                songService.searchByField("artist", artist));
        if (album != null)
            return ResponseEntity.ok(
                songService.searchByField("album", album));
        if (musicDirector != null)
            return ResponseEntity.ok(
                songService.searchByField(
                    "musicDirector", musicDirector));

        return ResponseEntity.ok(
            songService.getAllSongs());
    }

    // ADMIN SEARCH — includes hidden songs
    @GetMapping("/api/admin/songs/search")
    public ResponseEntity<?> adminSearchSongs(
            @RequestParam(required = false) String q) {
        if (q != null && !q.isBlank())
            return ResponseEntity.ok(
                songService.adminSearchSongs(q));
        return ResponseEntity.ok(
            songService.getAllSongsAdmin());
    }

    // Create song
    @PostMapping("/api/admin/songs")
    public ResponseEntity<?> createSong(
            @RequestBody SongCreateRequest request) {
        return ResponseEntity.ok(
            songService.createSong(request));
    }

    // Update song
    @PutMapping("/api/admin/songs/{id}")
    public ResponseEntity<?> updateSong(
            @PathVariable Long id,
            @RequestBody SongCreateRequest request) {
        return ResponseEntity.ok(
            songService.updateSong(id, request));
    }

    // Delete song
    @DeleteMapping("/api/admin/songs/{id}")
    public ResponseEntity<?> deleteSong(
            @PathVariable Long id) {
        songService.deleteSong(id);
        return ResponseEntity.ok(
            Map.of("message", "Song deleted successfully"));
    }

    // Toggle visibility
    @PutMapping("/api/admin/songs/{id}/visibility")
    public ResponseEntity<?> toggleVisibility(
            @PathVariable Long id) {
        return ResponseEntity.ok(
            songService.toggleVisibility(id));
    }
}