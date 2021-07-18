package com.example.service.trials;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ForegroundServiceCopied extends JobIntentService {

  private static final String CHANNEL_WHATEVER = "channel_whatever";
  private static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";
  private static final int NOTIFY_ID = 1337;
  private static final int FOREGROUND_ID = 1338;

  @Override
  public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
    Log.i("Foreground Service: ", "Started foreground service!");
    return super.onStartCommand(intent, flags, startId);
  }

  @Override
  protected void onHandleWork(@NonNull Intent intent) {
    Log.i("Foreground Service: ", "Inside onHandleWork");
    NotificationManager mgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
      Log.i("Foreground Service: ", "Inside SKD >= 26");

      NotificationChannel c =
          new NotificationChannel(
              CHANNEL_WHATEVER, "Whatever", NotificationManager.IMPORTANCE_DEFAULT);

      c.enableLights(true);
      c.setLightColor(0xFFFF0000);
      mgr.createNotificationChannel(c);
    }

    String filename = intent.getData().getLastPathSegment();

    startForeground(FOREGROUND_ID, buildForegroundNotification(filename));

    try {
      File output = new File(getFilesDir(), filename);

      if (output.exists()) {
        boolean deleted = output.delete();
        if (deleted) {
          Log.i("Foreground Service", "File Deleted!");
        }
      } else {
        Log.i("Foreground Service: ", "File not found!");
      }

      URL url = new URL(intent.getData().toString());
      HttpURLConnection c = (HttpURLConnection) url.openConnection();
      FileOutputStream fos = new FileOutputStream(output.getPath());

      try (BufferedOutputStream out = new BufferedOutputStream(fos)) {
        InputStream in = c.getInputStream();
        byte[] buffer = new byte[8192];
        int len;

        while ((len = in.read(buffer)) >= 0) {
          out.write(buffer, 0, len);
        }

        out.flush();
      } finally {
        fos.getFD().sync();
        c.disconnect();
      }

      raiseNotification(intent, output, null);
    } catch (IOException e2) {
      raiseNotification(intent, null, e2);
    } finally {
      stopForeground(true);
    }
  }

  private void raiseNotification(Intent inbound, File output, Exception e) {
    NotificationCompat.Builder b = new NotificationCompat.Builder(this, CHANNEL_WHATEVER);

    b.setAutoCancel(true).setWhen(System.currentTimeMillis());

    if (e == null) {
      b.setContentTitle(getString(R.string.download_complete))
          .setContentText(getString(R.string.fun))
          .setSmallIcon(android.R.drawable.stat_sys_download_done);

      Intent outbound = new Intent(Intent.ACTION_VIEW);
      Uri outputUri = FileProvider.getUriForFile(this, AUTHORITY, output);

      outbound.setDataAndType(outputUri, inbound.getType());
      outbound.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

      PendingIntent pi =
          PendingIntent.getActivity(this, 0, outbound, PendingIntent.FLAG_UPDATE_CURRENT);

      b.setContentIntent(pi);
    } else {
      b.setContentTitle(getString(R.string.exception))
          .setContentText(e.getMessage())
          .setSmallIcon(android.R.drawable.stat_notify_error);
    }

    NotificationManager mgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    mgr.notify(NOTIFY_ID, b.build());
  }

  private Notification buildForegroundNotification(String filename) {
    Log.i("Foreground Service: ", "Starting notification");
    NotificationCompat.Builder b = new NotificationCompat.Builder(this, CHANNEL_WHATEVER);

    b.setOngoing(true)
        .setContentTitle(getString(R.string.downloading))
        .setContentText(filename)
        .setSmallIcon(android.R.drawable.stat_sys_download);

    return b.build();
  }
}
