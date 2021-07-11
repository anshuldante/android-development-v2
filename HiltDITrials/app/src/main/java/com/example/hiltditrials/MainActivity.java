package com.example.hiltditrials;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hiltditrials.dependency.Printer;
import com.example.hiltditrials.dependency.TextMaker;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Inject
    TextMaker textMaker;

    @Inject
    @Named("log")
    Printer logPrinter;

    @Inject
    @Named("toast")
    Printer toastPrinter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String text = textMaker.getNiceText();
        logPrinter.print(text);
        toastPrinter.print(text);
    }
}