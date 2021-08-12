package com.test.myapplication;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class DelayedPrintWorker extends Worker {
  public DelayedPrintWorker(@NonNull Context context, @NonNull WorkerParameters params) {
    super(context, params);
  }

  @NonNull
  @Override
  public Result doWork() {
    Log.i("Delayed Print Worker", "Printing after a specified delay");
    return Result.success();
  }
}
