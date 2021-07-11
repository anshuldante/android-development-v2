package com.example.hiltditrials.dependency;

import android.util.Log;

import javax.inject.Inject;

public class LogPrinter implements Printer {

    @Inject
    public LogPrinter() {
    }

    @Override
    public void print(String str) {
        Log.i("Printer: ", str);
    }
}
