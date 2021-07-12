package com.example.hiltditrials.dependency;

import android.util.Log;

public class LogPrinter extends Printer {

    public LogPrinter(TextMaker textMaker) {
        super(textMaker);
    }

    @Override
    public void print() {
        Log.i("Printer: ", getText());
    }
}
