package com.example.roomtrials;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Library.class, Playlist.class, Song.class, PlaylistSongCrossRef.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract LibraryDao libraryDao();
}