package com.test.notesapptrial.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "note_table")
public class Note {
  @PrimaryKey(autoGenerate = true)
  private int id;

  private final String title;
  private final String description;
  private final int priority;

  public Note(String title, String description, int priority) {
    this.title = title;
    this.description = description;
    this.priority = priority;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public int getPriority() {
    return priority;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Note note = (Note) o;
    return id == note.id
        && priority == note.priority
        && title.equals(note.title)
        && description.equals(note.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, priority);
  }
}
