package com.test.notesapptrial.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.test.notesapptrial.model.Note;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
  private final NoteRepository repository;
  private final LiveData<List<Note>> allNotes;

  public NoteViewModel(@NonNull Application application) {
    super(application);
    repository = new NoteRepository(application);
    allNotes = repository.getAllNotes();
  }

  public void update(Note note) {
    repository.update(note);
  }

  public void insert(Note note) {
    repository.insert(note);
  }

  public void delete(Note note) {
    repository.delete(note);
  }

  public void deleteAll() {
    repository.deleteAllNotes();
  }

  public LiveData<List<Note>> getAllNotes() {
    return allNotes;
  }
}
