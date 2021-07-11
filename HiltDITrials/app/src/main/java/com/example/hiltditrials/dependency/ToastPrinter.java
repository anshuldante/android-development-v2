package com.example.hiltditrials.dependency;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

public class ToastPrinter implements Printer {

    private final Context context;

    @Inject
    public ToastPrinter(Context context) {
        this.context = context;
    }

    @Override
    public void print(String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }
}
