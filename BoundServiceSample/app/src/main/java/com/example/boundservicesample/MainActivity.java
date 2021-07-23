package com.example.boundservicesample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
  LocalService mService;
  boolean mBound = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override
  protected void onStart() {
    super.onStart();
    Intent intent = new Intent(this, LocalService.class);
    bindService(intent, connection, Context.BIND_AUTO_CREATE);
  }

  @Override
  protected void onStop() {
    super.onStop();
    unbindService(connection);
    mBound = false;
  }

  public void onButtonClick(View v) {
    Log.i("Bound service: ", "onButtonClick called");
    if (mBound) {
      // Call a method from the LocalService.
      // However, if this call were something that might hang, then this request should
      // occur in a separate thread to avoid slowing down the activity performance.
      int num = mService.getRandomNumber();
      Log.i("Bound service: ", "service bound!");
      Toast.makeText(this, "number: " + num, Toast.LENGTH_LONG).show();
    }
  }

  /** Defines callbacks for service binding, passed to bindService() */
  private final ServiceConnection connection =
      new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
          // We've bound to LocalService, cast the IBinder and get LocalService instance
          LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
          mService = binder.getService();
          mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
          mBound = false;
        }
      };
}
