package com.aahuf.converterapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.aahuf.converterapp.currency.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}