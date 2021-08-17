package com.test.workmanager.first.cut;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.time.Duration;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    OneTimeWorkRequest.Builder builder = new OneTimeWorkRequest.Builder(DelayedPrintWorker.class);
    OneTimeWorkRequest uploadWorkRequest = null;
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
      uploadWorkRequest = builder.setInitialDelay(Duration.ofSeconds(10)).build();
    } else {
      uploadWorkRequest = builder.build();
    }
    WorkManager.getInstance(this).enqueue(uploadWorkRequest);
  }
}
