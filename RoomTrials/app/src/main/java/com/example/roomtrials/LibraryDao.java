package com.example.roomtrials;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface LibraryDao {

    @Insert
    void insert(Library library);

    @Query("Select * from library")
    List<Library> getAll();


    @Query("DELETE FROM library")
    void nukeTable();

    @Transaction
    @Query("SELECT * FROM Playlist")
    public List<PlaylistWithSongs> getPlaylistsWithSongs();

    @Transaction
    @Query("SELECT * FROM Song")
    public List<SongWithPlaylists> getSongsWithPlaylists();
}
