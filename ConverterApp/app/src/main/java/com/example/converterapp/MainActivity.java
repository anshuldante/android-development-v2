package com.example.converterapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.converterapp.currency.adapter.CurrencyItemAdapter;
import com.example.converterapp.currency.data.CurrencyData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView currencyRecyclerView = findViewById(R.id.currency_converter_recycler_view);
        currencyRecyclerView.setAdapter(new CurrencyItemAdapter(CurrencyData.getCurrencyModels()));
    }
}