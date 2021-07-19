package com.example.service.trials;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private Intent serviceIntent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    startForegroundService();

    //    foregroundServiceWithFragment();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    stopService(serviceIntent);
  }

  private void foregroundServiceWithFragment() {
    if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
      getSupportFragmentManager()
          .beginTransaction()
          .add(android.R.id.content, new FirstFragment())
          .commit();
    }
  }

  private void startForegroundService() {
    serviceIntent = new Intent(this, ForegroundService.class);
    Log.i("Foreground Service: ", "Creating Foreground Service Intent");
    startService(serviceIntent);
  }
}
