package com.test.wakelockorkepcpuon;

import android.content.Context;
import android.util.Log;

public class EverRunningTask implements Runnable {

  private final Context context;

  public EverRunningTask(Context context) {
    this.context = context;
  }

  @Override
  public void run() {
    for (int iteration = 1; iteration < 100; iteration++) {
      Log.i("Printing:", "Long running task printed: " + iteration);
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException e) {
        Log.e("ERROR!", "Long running task interrupted!", e);
        Thread.currentThread().interrupt();
      }
    }
  }
}
