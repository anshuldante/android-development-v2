package com.test.notificationone;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.Person;

public class MainActivity extends AppCompatActivity {

  private static final String CHANNEL_ID = "com.test.notificationone";
  public static final String LARGE_MSG =
      "Much more detailed content <b>that can have buttons and images etc.</b>\n containing a new line to test stuff out properly";
  public static final String SHORT_MSG = "Primary message with high level details";
  private static final String ACTION_SNOOZE = "Snooze";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
    createNotificationChannel();

    NotificationCompat.Builder notificationTrialBuilder = trialNotification();

    NotificationCompat.Builder highPriorityNotificationBuilder = highPriorityNotification();

    notificationManager.notify(1, notificationTrialBuilder.build());
  }

  private NotificationCompat.Builder highPriorityNotification() {
    Intent fullScreenIntent = new Intent(this, FullscreenActivity.class);
    PendingIntent fullScreenPendingIntent =
        PendingIntent.getActivity(this, 0, fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    return new NotificationCompat.Builder(this, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_baseline_snooze_24)
        .setContentTitle("Incoming call")
        .setContentText("(919) 555-1234")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setCategory(NotificationCompat.CATEGORY_CALL)
        .setFullScreenIntent(fullScreenPendingIntent, true);
  }

  private NotificationCompat.Builder trialNotification() {
    NotificationCompat.Builder builder = createNotification();

    attachActivityIntent(builder);
    attachBroadcastIntent(builder);
    attachSnoozeAction(builder);
    attachDismissAction(builder);
    attachFullscreenIntent(builder);

    attachLargeImageAndIcon(builder);

    addMultipleLines(builder);

    addConversation(builder);
    return builder;
  }

  private void addConversation(NotificationCompat.Builder builder) {
    NotificationCompat.MessagingStyle.Message message1 =
        new NotificationCompat.MessagingStyle.Message(
            "Hi Vibhuti",
            System.currentTimeMillis(),
            new Person.Builder().setName("Anshul").build());

    NotificationCompat.MessagingStyle.Message message2 =
        new NotificationCompat.MessagingStyle.Message(
            "Hi Anshul",
            System.currentTimeMillis(),
            new Person.Builder().setName("Vibhuti").build());

    builder.setStyle(
        new NotificationCompat.MessagingStyle(new Person.Builder().setName("Anshul").build())
            .addMessage(message1)
            .addMessage(message2)
            .setConversationTitle("Sample chat"));
  }

  private void addMultipleLines(NotificationCompat.Builder builder) {
    builder.setStyle(
        new NotificationCompat.InboxStyle()
            .addLine("Message for line-1")
            .addLine("Message for line-2"));
  }

  private void attachLargeImageAndIcon(NotificationCompat.Builder builder) {
    Bitmap imageIcon =
        BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.infinite));

    builder
        .setLargeIcon(imageIcon)
        .setStyle(
            new NotificationCompat.BigPictureStyle().bigPicture(imageIcon).bigLargeIcon(null));
  }

  private void attachFullscreenIntent(NotificationCompat.Builder builder) {
    Intent fullScreenIntent = new Intent(this, FullscreenActivity.class);
    PendingIntent fullScreenPendingIntent =
        PendingIntent.getActivity(this, 0, fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    builder.setFullScreenIntent(fullScreenPendingIntent, true);
  }

  private void attachDismissAction(NotificationCompat.Builder builder) {}

  private void attachSnoozeAction(NotificationCompat.Builder builder) {}

  private void attachBroadcastIntent(NotificationCompat.Builder builder) {
    Intent snoozeIntent = new Intent(this, MyBroadcastReceiver.class);
    snoozeIntent.setAction(ACTION_SNOOZE);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
    }
    PendingIntent snoozePendingIntent = PendingIntent.getBroadcast(this, 0, snoozeIntent, 0);
    builder.addAction(
        R.drawable.ic_baseline_snooze_24, getString(R.string.snooze), snoozePendingIntent);
  }

  private void attachActivityIntent(NotificationCompat.Builder builder) {

    // Create an explicit intent for an Activity in your app
    Intent intent = new Intent(this, BroadcastActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
    builder.setContentIntent(pendingIntent);
  }

  private NotificationCompat.Builder createNotification() {
    return new NotificationCompat.Builder(this, CHANNEL_ID)
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
      CharSequence name = getString(R.string.channel_name);
      String description = getString(R.string.channel_description);
      int importance = NotificationManager.IMPORTANCE_DEFAULT;
      NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
      channel.setDescription(description);
      // Register the channel with the system; you can't change the importance
      // or other notification behaviors after this
      NotificationManager notificationManager = getSystemService(NotificationManager.class);
      notificationManager.createNotificationChannel(channel);
    }
  }
}
