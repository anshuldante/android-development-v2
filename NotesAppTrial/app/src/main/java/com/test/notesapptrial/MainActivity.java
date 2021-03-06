package com.test.notesapptrial;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.test.notesapptrial.data.NoteViewModel;
import com.test.notesapptrial.model.Note;
import com.test.notesapptrial.view.NoteAdapter;

public class MainActivity extends AppCompatActivity {
  public static final int ADD_NOTE_REQUEST = 1;
  public static final int EDIT_NOTE_REQUEST = 2;
  private NoteViewModel noteViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    FloatingActionButton fab = findViewById(R.id.button_add_note);
    fab.setOnClickListener(
        v -> {
          Intent intent = new Intent(MainActivity.this, AddEditNoteActivity.class);
          startActivityForResult(intent, ADD_NOTE_REQUEST);
        });

    RecyclerView recyclerView = findViewById(R.id.recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setHasFixedSize(true);
    final NoteAdapter adapter = new NoteAdapter();
    recyclerView.setAdapter(adapter);

    noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
    noteViewModel
        .getAllNotes()
        .observe(
            this,
            (notes) -> {
              Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_LONG).show();
              adapter.submitList(notes);
            });

    new ItemTouchHelper(
            new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
              @Override
              public boolean onMove(
                  @NonNull RecyclerView recyclerView,
                  @NonNull RecyclerView.ViewHolder viewHolder,
                  @NonNull RecyclerView.ViewHolder target) {
                return false;
              }

              @Override
              public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
              }
            })
        .attachToRecyclerView(recyclerView);

    adapter.setOnItemClickListener(
        note -> {
          Intent intent = new Intent(MainActivity.this, AddEditNoteActivity.class);
          intent.putExtra(AddEditNoteActivity.EXTRA_ID, note.getId());
          intent.putExtra(AddEditNoteActivity.EXTRA_TITLE, note.getTitle());
          intent.putExtra(AddEditNoteActivity.EXTRA_DESCRIPTION, note.getDescription());
          intent.putExtra(AddEditNoteActivity.EXTRA_PRIORITY, note.getPriority());
          startActivityForResult(intent, EDIT_NOTE_REQUEST);
        });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK && data != null) {
      String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
      String description = data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION);
      int priority = data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY, 1);

      Note note = new Note(title, description, priority);

      noteViewModel.insert(note);
      Toast.makeText(this, "Note saved!", Toast.LENGTH_LONG).show();
    } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK && data != null) {
      int id = data.getIntExtra(AddEditNoteActivity.EXTRA_ID, -1);

      if (id == -1) {
        Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
        return;
      }

      String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
      String description = data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION);
      int priority = data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY, 1);

      Note note = new Note(title, description, priority);
      note.setId(id);
      noteViewModel.update(note);
      Toast.makeText(this, "Note updated!", Toast.LENGTH_LONG).show();
    } else {
      Toast.makeText(this, "Note not saved!", Toast.LENGTH_LONG).show();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {

    if (item.getItemId() == R.id.delete_all_notes) {
      noteViewModel.deleteAll();
      Toast.makeText(this, "All notes deleted!", Toast.LENGTH_SHORT).show();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
