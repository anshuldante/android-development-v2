package com.example.service.trials;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {
  Intent serviceIntent;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    View result = inflater.inflate(R.layout.activity_main, parent, false);

    Button downloadButton = result.findViewById(R.id.am_download);
    downloadButton.setOnClickListener(this::downloadFile);

    Button backgroundStartButton = result.findViewById(R.id.am_background_run);
    backgroundStartButton.setOnClickListener(this::startBackgroundService);

    Button backgroundStopButton = result.findViewById(R.id.am_background_stop);
    backgroundStopButton.setOnClickListener(this::stopBackgroundService);

    return result;
  }

  public void downloadFile(View v) {
    Log.i("Foreground Service: ", "Starting Foreground Service");
    Intent i = new Intent(getActivity(), ForegroundServiceCopied.class);

    i.setDataAndType(
        Uri.parse("https://commonsware.com/Android/Android-1_0-CC.pdf"), "application/pdf");

    requireActivity().startService(i);
  }



  private void startBackgroundService(View v) {
    serviceIntent = new Intent(getActivity(), BackgroundService.class);
    Log.i("Background Service: ", "Creating Service Intent");
    requireActivity().startService(serviceIntent);
  }

  private void stopBackgroundService(View v) {
    Log.i("Background Service: ", "Stopping background service");
    requireActivity().stopService(serviceIntent);
  }
}
