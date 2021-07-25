package com.example.looperandhandler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  SimpleWorker worker = new SimpleWorker();
  private final Handler handler =
      new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
          super.handleMessage(msg);
          tv.setText(msg.obj.toString());
        }
      };
  private TextView tv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    tv = findViewById(R.id.tv_message);

    worker
        .execute(
            () -> {
              try {
                Thread.sleep(1000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              Message message = Message.obtain();
              message.obj = "Task 1 completed";
              handler.sendMessage(message);
            })
        .execute(
            () -> {
              try {
                Thread.sleep(1000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              Message message = Message.obtain();
              message.obj = "Task 2 completed";
              handler.sendMessage(message);
            })
        .execute(
            () -> {
              try {
                Thread.sleep(1000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              Message message = Message.obtain();
              message.obj = "Task 3 completed";
              handler.sendMessage(message);
            });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    worker.quit();
  }
}
