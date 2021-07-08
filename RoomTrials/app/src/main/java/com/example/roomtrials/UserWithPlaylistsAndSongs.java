package com.example.roomtrials;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithPlaylistsAndSongs {
    @Embedded
    public User user;
    @Relation(
            entity = Playlist.class,
            parentColumn = "id",
            entityColumn = "userCreatorId"
    )
    public List<PlaylistWithSongs> playlists;
}