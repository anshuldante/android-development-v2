package com.test.wakelockorkepcpuon;

import static android.os.PowerManager.PARTIAL_WAKE_LOCK;

import android.os.Bundle;
import android.os.PowerManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private PowerManager.WakeLock wakeLock;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
    wakeLock = powerManager.newWakeLock(PARTIAL_WAKE_LOCK, "WakeLockTrial::MyWakeLock");

    Button acquireLock = findViewById(R.id.am_bt_acquire);
    acquireLock.setOnClickListener(v -> acquireWakeLock());

    Button releaseLock = findViewById(R.id.am_bt_release);
    releaseLock.setOnClickListener(v -> releaseWakeLock());

    Button triggerTask = findViewById(R.id.am_bt_trigger_task);
    triggerTask.setOnClickListener(v -> triggerEverRunningTask());
  }

  private void triggerEverRunningTask() {
    new Thread(new EverRunningTask(this)).start();
  }

  private void acquireWakeLock() {
    wakeLock.acquire(60 * 1000L);
  }

  private void releaseWakeLock() {
    wakeLock.release();
  }
}
