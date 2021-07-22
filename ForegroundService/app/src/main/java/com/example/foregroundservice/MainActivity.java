package com.example.foregroundservice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
  private Intent serviceIntent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button stopButton = findViewById(R.id.stopService);
    stopButton.setOnClickListener(view -> stopForegroundService());

    Button startButton = findViewById(R.id.startService);
    startButton.setOnClickListener(view -> startForegroundService());

    startForegroundService();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  private void stopForegroundService() {
    stopService(serviceIntent);
  }

  private void startForegroundService() {
    if (serviceIntent != null) {
      stopForegroundService();
    }
    serviceIntent = new Intent(this, ForegroundService.class);
    Log.i("Foreground Service: ", "Creating Foreground Service Intent");
    startService(serviceIntent);
  }
}
