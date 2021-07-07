package com.example.roomtrials;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Playlist {
    @PrimaryKey
    public long playlistId;
    public long userCreatorId;
    public String playlistName;
}