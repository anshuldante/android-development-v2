package com.test.notesapptrial.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.test.notesapptrial.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

  @Insert
  void insert(Note note);

  @Update
  void update(Note note);

  @Delete
  void delete(Note note);

  @Query("DELETE FROM note_table")
  void deleteAllNotes();

  @Query("SELECT * from note_table order by priority DESC")
  LiveData<List<Note>> getAllNotes();
}
