package com.test.samplebroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
  private static final String TAG = "MyBroadcastReceiver";

  @Override
  public void onReceive(Context context, Intent intent) {
    final PendingResult pendingResult = goAsync();
    Task asyncTask = new Task(pendingResult, intent);
    asyncTask.execute();
  }

  private static class Task extends AsyncTask<String, Integer, String> {

    private final PendingResult pendingResult;
    private final Intent intent;

    private Task(PendingResult pendingResult, Intent intent) {
      this.pendingResult = pendingResult;
      this.intent = intent;
    }

    @Override
    protected String doInBackground(String... strings) {
      String log =
          "Action: "
              + intent.getAction()
              + "\n"
              + "URI: "
              + intent.toUri(Intent.URI_INTENT_SCHEME).toString()
              + "\n";
      Log.i(TAG, log);
      return log;
    }

    @Override
    protected void onPostExecute(String s) {
      super.onPostExecute(s);
      pendingResult.finish();
    }
  }
}
