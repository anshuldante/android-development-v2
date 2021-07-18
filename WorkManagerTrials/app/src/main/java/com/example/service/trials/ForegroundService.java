package com.example.service.trials;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class ForegroundService extends Service {
  private static final int ONGOING_NOTIFICATION_ID = 1243;

  @Override
  public void onCreate() {
    super.onCreate();
    Log.i("Foreground Service: ", "Service Created");
    HandlerThread thread =
        new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_FOREGROUND);
    thread.start();

    Looper serviceLooper = thread.getLooper();
    ServiceHandler serviceHandler = new ServiceHandler(serviceLooper);
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Log.i("Foreground Service: ", "onBind called");

    String NOTIFICATION_CHANNEL_ID = " com.example.service.trials";
    String channelName = "My Foreground Service";

    NotificationChannel channel =
        new NotificationChannel(
            NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
    channel.setLightColor(Color.BLUE);
    channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
    NotificationManager manager =
        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    assert manager != null;
    manager.createNotificationChannel(channel);

    Intent notificationIntent = new Intent(this, MainActivity.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

    Notification notification =
        new Notification.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle(getText(R.string.notification_title))
            .setContentText(getText(R.string.notification_message))
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .setTicker(getText(R.string.ticker_text))
            .build();

    startForeground(ONGOING_NOTIFICATION_ID, notification);
    return super.onStartCommand(intent, flags, startId);
  }

  @Override
  public void onDestroy() {
    Log.i("Foreground Service: ", "Service Stopped");
    Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
  }

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  private static final class ServiceHandler extends Handler {
    public ServiceHandler(Looper looper) {
      super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
      Log.i("Foreground Service: ", "Handling Message");
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      Log.i("Foreground Service: ", msg.getData().keySet().toString());
    }
  }
}
