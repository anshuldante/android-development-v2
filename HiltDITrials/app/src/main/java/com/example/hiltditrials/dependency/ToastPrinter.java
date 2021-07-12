package com.example.hiltditrials.dependency;

import android.content.Context;
import android.widget.Toast;

public class ToastPrinter extends Printer {

    private final Context context;

    public ToastPrinter(TextMaker textMaker, Context context) {
        super(textMaker);
        this.context = context;
    }

    @Override
    public void print() {
        Toast.makeText(context, getText(), Toast.LENGTH_LONG).show();
    }
}
