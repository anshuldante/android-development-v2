package com.test.notificationone;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_alarm);

    TextView alarmText = findViewById(R.id.alarm_text);
    alarmText.setText("Alert service invoked");
    Toast.makeText(this, R.string.service_text, Toast.LENGTH_LONG).show();
  }
}
