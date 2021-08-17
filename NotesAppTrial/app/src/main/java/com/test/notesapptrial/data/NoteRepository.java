package com.test.notesapptrial.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.test.notesapptrial.model.Note;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {
  private final NoteDao noteDao;
  private final LiveData<List<Note>> allNotes;
  private final ExecutorService executorService;

  public NoteRepository(Context context) {
    noteDao = NoteDatabase.getInstance(context).noteDao();
    allNotes = noteDao.getAllNotes();
    executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    addSampleData();
  }

  private void addSampleData() {
    insert(new Note("Title 1", "Description 1", 1));
    insert(new Note("Title 2", "Description 2", 2));
    insert(new Note("Title 3", "Description 3", 3));
  }

  public void insert(Note note) {
    executorService.submit(() -> noteDao.insert(note));
  }

  public void update(Note note) {
    executorService.submit(() -> noteDao.update(note));
  }

  public void delete(Note note) {
    executorService.submit(() -> noteDao.delete(note));
  }

  public void deleteAllNotes() {
    executorService.submit(noteDao::deleteAllNotes);
  }

  public LiveData<List<Note>> getAllNotes() {
    return allNotes;
  }
}
