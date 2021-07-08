package com.example.roomtrials;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithPlaylists {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "id",
            entityColumn = "userCreatorId"
    )
    public List<Playlist> playlists;
}