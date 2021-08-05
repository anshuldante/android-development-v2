package com.test.alarmmanagertrials;

import static android.app.AlarmManager.ELAPSED_REALTIME_WAKEUP;
import static android.app.AlarmManager.RTC_WAKEUP;
import static android.app.PendingIntent.FLAG_IMMUTABLE;
import static android.app.PendingIntent.getActivity;
import static android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
import static android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
import static android.content.pm.PackageManager.DONT_KILL_APP;
import static android.os.SystemClock.elapsedRealtime;
import static java.util.Calendar.MINUTE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
  private static final int REQUEST_CODE = 1234;

  private PackageManager pm;
  private Intent serviceIntent;
  private AlarmManager alarmMgr;
  private ComponentName receiver;
  private PendingIntent alarmIntent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    alarmMgr = (AlarmManager) getSystemService(ALARM_SERVICE);
    receiver = new ComponentName(this, SampleBootReceiver.class);
    pm = this.getPackageManager();

    serviceIntent = new Intent(this, ForegroundServiceActivity.class);
    alarmIntent = getActivity(this, REQUEST_CODE, serviceIntent, FLAG_IMMUTABLE);

    //    startForegroundService();
    //    alarmInTenSeconds();
    //    inexactAlarmRepeatedEveryMinute();
    //    alarmEveryAlternateMinute();
    enableBroadcastReceiver();
  }

  private void startForegroundService() {
    Log.i("Foreground Service: ", "Creating Foreground Service Intent");
    startActivity(serviceIntent);
  }

  private void alarmInTenSeconds() {
    alarmMgr.set(ELAPSED_REALTIME_WAKEUP, elapsedRealtime() + 10 * 1000, alarmIntent);
  }

  private void inexactAlarmRepeatedEveryMinute() {
    Log.i("Foreground Service: ", "Notification every 30 seconds");
    alarmMgr.setInexactRepeating(
        ELAPSED_REALTIME_WAKEUP, elapsedRealtime() + 10 * 1000L, 60 * 1000L, alarmIntent);
  }

  private void alarmEveryAlternateMinute() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    calendar.add(MINUTE, 1);

    alarmMgr.setInexactRepeating(RTC_WAKEUP, calendar.getTimeInMillis(), 60 * 1000L, alarmIntent);
  }

  private void alarmAtExactTimeRepeatingEveryTwoMinutes() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    calendar.add(MINUTE, 1);

    alarmMgr.setRepeating(RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 2, alarmIntent);
  }

  private void enableBroadcastReceiver() {
    pm.setComponentEnabledSetting(receiver, COMPONENT_ENABLED_STATE_ENABLED, DONT_KILL_APP);
  }

  private void disableBroadcastReceiver() {
    pm.setComponentEnabledSetting(receiver, COMPONENT_ENABLED_STATE_DISABLED, DONT_KILL_APP);
  }

  private void cancelAlarm() {
    if (alarmMgr != null) {
      alarmMgr.cancel(alarmIntent);
    }
  }
}
