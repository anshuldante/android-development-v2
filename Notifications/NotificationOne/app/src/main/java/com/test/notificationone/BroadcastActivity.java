package com.test.notificationone;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BroadcastActivity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Log.i("Alert Service", "Alert Activity invoked");
    Toast.makeText(this, R.string.service_text, Toast.LENGTH_LONG).show();
  }
}
