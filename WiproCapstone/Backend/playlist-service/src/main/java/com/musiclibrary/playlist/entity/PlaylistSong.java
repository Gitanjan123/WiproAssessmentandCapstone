package com.musiclibrary.playlist.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "playlist_songs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long playlistId;
    private Long songId;
}