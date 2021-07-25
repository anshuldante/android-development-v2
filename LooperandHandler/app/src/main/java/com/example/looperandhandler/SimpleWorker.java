package com.example.looperandhandler;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleWorker extends Thread {

  private static final String TAG = "SimpleWorker";
  private final AtomicBoolean alive = new AtomicBoolean(true);

  private final BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

  public SimpleWorker() {
    super(TAG);
    start();
  }

  @Override
  public void run() {
    while (alive.get()) {
      try {
        taskQueue.take().run();
      } catch (InterruptedException e) {
        Log.e("Task queue: ", "exception occurred while running a task", e);
      }
    }
  }

  public SimpleWorker execute(Runnable task) {
    try {
      taskQueue.put(task);
    } catch (InterruptedException e) {
      Log.e("Task queue: ", "exception occurred while accepting a task", e);
    }
    return this;
  }

  public void quit() {
    alive.set(false);
  }
}
