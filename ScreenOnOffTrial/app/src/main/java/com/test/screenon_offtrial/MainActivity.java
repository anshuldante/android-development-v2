package com.test.screenon_offtrial;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button screenOn = findViewById(R.id.am_bt_on);
    screenOn.setOnClickListener(v -> keepScreenOn());

    Button screenOff = findViewById(R.id.am_bt_off);
    screenOff.setOnClickListener(v -> letScreenTurnOff());
  }

  private void keepScreenOn() {
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    Toast.makeText(this, "Will keep screen on now!", Toast.LENGTH_LONG).show();
  }

  private void letScreenTurnOff() {
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    Toast.makeText(this, "Will let screen turn off now!", Toast.LENGTH_LONG).show();
  }
}
