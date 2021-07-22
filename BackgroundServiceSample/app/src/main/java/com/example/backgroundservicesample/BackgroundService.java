package com.example.backgroundservicesample;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

public class BackgroundService extends Service {
  private ServiceHandler serviceHandler;

  @Override
  public void onCreate() {
    Log.i("Background Service: ", "Service Created");
    HandlerThread thread =
        new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
    thread.start();

    Looper serviceLooper = thread.getLooper();
    serviceHandler = new ServiceHandler(serviceLooper);
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Log.i("Background Service: ", "Service Starting");
    Toast.makeText(this, "service starting", Toast.LENGTH_LONG).show();

    Message msg = serviceHandler.obtainMessage();
    msg.arg1 = startId;
    serviceHandler.sendMessage(msg);

    return START_NOT_STICKY;
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public boolean onUnbind(Intent intent) {
    return true;
  }

  @Override
  public void onRebind(Intent intent) {}

  @Override
  public void onDestroy() {
    Log.i("Background Service: ", "Service Stopped");
    Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
  }

  private static final class ServiceHandler extends Handler {
    public ServiceHandler(Looper looper) {
      super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
      Log.i("Background Service: ", "Handling Message");
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      Log.i("Background Service: ", msg.getData().keySet().toString());
      // stopSelf(msg.arg1);
    }
  }
}
