package com.example.hiltditrials;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hiltditrials.dependency.Printer;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

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

        logPrinter.print();
        toastPrinter.print();
    }
}