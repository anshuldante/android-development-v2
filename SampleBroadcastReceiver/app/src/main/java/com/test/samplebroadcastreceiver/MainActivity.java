package com.test.samplebroadcastreceiver;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private BroadcastReceiver broadcastReceiver;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    broadcastReceiver = new MyBroadcastReceiver();
    IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
    this.registerReceiver(broadcastReceiver, filter);

    // Receive with permission
    //    registerReceiver(broadcastReceiver, filter, Manifest.permission.SEND_SMS, null);
  }

  private void sendBroadcast() {
    Intent intent = new Intent();
    intent.setAction("com.example.broadcast.MY_NOTIFICATION");
    intent.putExtra("data", "Nothing to see here, move along.");
    sendBroadcast(intent, Manifest.permission.SEND_SMS);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    unregisterReceiver(broadcastReceiver);
  }
}
