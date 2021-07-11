package com.example.hiltditrials;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class HiltApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
