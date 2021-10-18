package com.test.notificationone;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyBroadcastReceiver extends BroadcastReceiver {
  private static final String TAG = "com.test.notificationone.MyBroadcastReceiver";

  private static final String CHANNEL_ID = "com.test.notificationone";
  public static final String LARGE_MSG =
      "Much more detailed content that can have buttons and images etc.\n containing a new line to test stuff out properly";
  public static final String SHORT_MSG = "Primary message with high level details";
  private static final String ACTION_SNOOZE = "Snooze";

  private Context context;

  @Override
  public void onReceive(Context context, Intent intent) {
    String log =
        "Action: "
            + intent.getAction()
            + "\n"
            + "URI: "
            + intent.toUri(Intent.URI_INTENT_SCHEME)
            + "\n";
    Log.i(TAG, log);
    this.context = context;
    Toast.makeText(context, log, Toast.LENGTH_LONG).show();

    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
    notificationManager.cancelAll();

    new Thread(
            () -> {
              Log.i(TAG, "Will sleep and trigger another notification.");
              try {
                Thread.sleep(2000l);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }

              Log.i(TAG, "Notification triggered!!");
              createNotificationChannel();
              NotificationCompat.Builder builder = createNotification();

              attachActivityIntent(builder);
              attachBroadcastIntent(builder);
              attachSnoozeAction(builder);
              attachDismissAction(builder);
              attachFullscreenIntent(builder);
              notificationManager.notify(1, builder.build());
            })
        .start();
  }

  private void attachFullscreenIntent(NotificationCompat.Builder builder) {
    Intent fullScreenIntent = new Intent(context, FullscreenActivity.class);
    PendingIntent fullScreenPendingIntent =
        PendingIntent.getActivity(context, 0, fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    builder.setFullScreenIntent(fullScreenPendingIntent, true);
  }

  private void attachDismissAction(NotificationCompat.Builder builder) {}

  private void attachSnoozeAction(NotificationCompat.Builder builder) {}

  private void attachBroadcastIntent(NotificationCompat.Builder builder) {
    Intent snoozeIntent = new Intent(context, MyBroadcastReceiver.class);
    snoozeIntent.setAction(ACTION_SNOOZE);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
    }
    PendingIntent snoozePendingIntent = PendingIntent.getBroadcast(context, 0, snoozeIntent, 0);
    builder.addAction(
        R.drawable.ic_baseline_snooze_24, context.getString(R.string.snooze), snoozePendingIntent);
  }

  private void attachActivityIntent(NotificationCompat.Builder builder) {

    // Create an explicit intent for an Activity in your app
    Intent intent = new Intent(context, BroadcastActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
    builder.setContentIntent(pendingIntent);
  }

  private NotificationCompat.Builder createNotification() {
    return new NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_sharp_build_24)
        .setContentTitle("My notification")
        .setContentText(SHORT_MSG)
        // Allows one to create a larger text area than default
        .setStyle(new NotificationCompat.BigTextStyle().bigText(LARGE_MSG))
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setCategory(NotificationCompat.CATEGORY_ALARM)
        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        // Notification gets cancelled when the user clicks it from the panel.
        .setAutoCancel(true);
  }

  private void createNotificationChannel() {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      CharSequence name = context.getString(R.string.channel_name);
      String description = context.getString(R.string.channel_description);
      int importance = NotificationManager.IMPORTANCE_DEFAULT;
      NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
      channel.setDescription(description);
      // Register the channel with the system; you can't change the importance
      // or other notification behaviors after this
      NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
      notificationManager.createNotificationChannel(channel);
    }
  }
}
