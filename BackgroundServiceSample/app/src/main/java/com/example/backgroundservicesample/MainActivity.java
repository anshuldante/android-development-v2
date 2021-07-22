package com.example.backgroundservicesample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    foregroundServiceWithFragment();
  }

  private void foregroundServiceWithFragment() {
    if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
      getSupportFragmentManager()
          .beginTransaction()
          .add(android.R.id.content, new FirstFragment())
          .commit();
    }
  }
}
