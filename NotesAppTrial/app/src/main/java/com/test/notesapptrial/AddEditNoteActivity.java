package com.test.notesapptrial;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AddEditNoteActivity extends AppCompatActivity {

  public static final String EXTRA_ID = "com.test.notesapptrial.EXTRA_ID";
  public static final String EXTRA_TITLE = "com.test.notesapptrial.EXTRA_TITLE";
  public static final String EXTRA_DESCRIPTION = "com.test.notesapptrial.EXTRA_DESCRIPTION";
  public static final String EXTRA_PRIORITY = "com.test.notesapptrial.EXTRA_PRIORITY";

  private EditText editTextTitle;
  private EditText editTextDescription;
  private NumberPicker numberPickerPriority;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_note);

    editTextTitle = findViewById(R.id.et_title);
    editTextDescription = findViewById(R.id.et_description);
    numberPickerPriority = findViewById(R.id.np_priority);
    numberPickerPriority.setMinValue(1);
    numberPickerPriority.setMaxValue(10);

    Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close);

    Intent intent = getIntent();

    if (intent.hasExtra(EXTRA_ID)) {
      setTitle("Edit Note");
      editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
      editTextDescription.setText(intent.getStringExtra(EXTRA_TITLE));
      numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
    } else {
      setTitle("Add Note");
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.add_note_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.save_note) {
      saveNote();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void saveNote() {
    String title = editTextTitle.getText().toString();
    String description = editTextDescription.getText().toString();
    int priority = numberPickerPriority.getValue();

    if (title.trim().isEmpty() || description.isEmpty()) {
      Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_LONG).show();
      return;
    }

    Intent data = new Intent();
    data.putExtra(EXTRA_TITLE, title);
    data.putExtra(EXTRA_DESCRIPTION, description);
    data.putExtra(EXTRA_PRIORITY, priority);
    int id = getIntent().getIntExtra(EXTRA_ID, -1);

    if (id != -1) {
      data.putExtra(EXTRA_ID, id);
    }
    setResult(RESULT_OK, data);
    finish();
  }
}
