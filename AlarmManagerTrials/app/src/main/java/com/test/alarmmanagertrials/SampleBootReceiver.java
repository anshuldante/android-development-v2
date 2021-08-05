package com.test.alarmmanagertrials;

import static android.app.AlarmManager.ELAPSED_REALTIME_WAKEUP;
import static android.app.PendingIntent.FLAG_IMMUTABLE;
import static android.app.PendingIntent.getActivity;
import static android.content.Context.ALARM_SERVICE;
import static android.os.SystemClock.elapsedRealtime;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SampleBootReceiver extends BroadcastReceiver {

  private static final int REQUEST_CODE = 5678;

  @Override
  public void onReceive(Context context, Intent intent) {
    if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

      AlarmManager alarmMgr = (AlarmManager) context.getSystemService(ALARM_SERVICE);
      PendingIntent alarmIntent =
          getActivity(
              context,
              REQUEST_CODE,
              new Intent(context, ForegroundServiceActivity.class),
              FLAG_IMMUTABLE);
      alarmMgr.set(ELAPSED_REALTIME_WAKEUP, elapsedRealtime() + 10 * 1000, alarmIntent);
    }
  }
}
