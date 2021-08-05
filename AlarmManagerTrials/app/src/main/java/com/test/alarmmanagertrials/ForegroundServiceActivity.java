package com.test.alarmmanagertrials;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class ForegroundServiceActivity extends AppCompatActivity {

  private Intent serviceIntent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    startForegroundService();

    stopForegroundServiceAfterDelay();
  }

  private void stopForegroundServiceAfterDelay() {
    new Thread(
            () -> {
              try {
                Thread.sleep(5000L);
                stopForegroundService();
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            })
        .start();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    stopForegroundService();
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
