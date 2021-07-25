package com.example.handlerlooperimproved;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

  private TextView textView;

  private Handler handler;

  private final AtomicInteger atomicInteger = new AtomicInteger(1);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button button = findViewById(R.id.bt_message);
    button.setOnClickListener(v -> pushMessage());
    textView = findViewById(R.id.tv_message);

    HandlerThread handlerThread = new HandlerThread("MyThread");
    handlerThread.start();
    Handler uiHandler =
        new Handler(Looper.getMainLooper()) {
          @Override
          public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            appendMessage(message);
          }
        };
    handler = new MyCustomHandler(handlerThread.getLooper(), uiHandler);
  }

  private void pushMessage() {
    Message message = Message.obtain();
    message.obj = "Message number: " + atomicInteger.getAndIncrement();
    handler.sendMessage(message);
  }

  private void appendMessage(Message message) {
    textView.setText(
        String.format(
            "%s\n%s",
            textView.getText() != null ? textView.getText() : "",
            message.obj != null ? message.obj.toString() : ""));
  }

  private static class MyCustomHandler extends Handler {
    private final Handler uiHandler;

    public MyCustomHandler(Looper looper, Handler uiHandler) {
      super(looper);
      this.uiHandler = uiHandler;
    }

    @Override
    public void handleMessage(@NonNull Message message) {
      super.handleMessage(message);
      Message outgoingMessage = Message.obtain();
      outgoingMessage.obj = message.obj;
      uiHandler.sendMessage(outgoingMessage);
    }
  }
}
