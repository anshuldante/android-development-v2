package com.example.converterapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.converterapp.currency.adapter.CurrencyItemAdapter;
import com.example.converterapp.currency.data.CurrencyData;
import com.example.converterapp.currency.model.ConversionRatesModel;

public class MainActivity extends AppCompatActivity {

    private CurrencyData currencyData;
    private ConversionRatesModel conversionRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupCurrencyConverter();
    }

    private void setupCurrencyConverter() {
        currencyData = getCurrencyData();
        conversionRates = currencyData.getConversionRates();

        RecyclerView currencyRecyclerView = findViewById(R.id.currency_converter_recycler_view);
        currencyRecyclerView.setAdapter(new CurrencyItemAdapter(currencyData.getCurrencyModels()));
    }

    private CurrencyData getCurrencyData() {
        if (currencyData == null) {
            currencyData = new CurrencyData(this);
        }
        return currencyData;


    }
}