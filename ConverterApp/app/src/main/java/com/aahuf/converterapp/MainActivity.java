package com.aahuf.converterapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aahuf.converterapp.currency.adapter.CurrencyItemAdapter;
import com.aahuf.converterapp.currency.data.CurrencyData;
import com.aahuf.converterapp.currency.model.ConversionRatesModel;

public class MainActivity extends AppCompatActivity {

    private CurrencyData currencyData;
    private SharedPreferences preferences;
    private ConversionRatesModel conversionRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupCurrencyConverter();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Log.i("Preferences: ", "Decimal places: " + preferences.getString("decimal_places_preference", ""));
        Log.i("Preferences: ", "Default Currency: " + preferences.getString("default_currency_preference", ""));
        Log.i("Preferences: ", "Selected Currencies: " + preferences.getStringSet("selected_currencies_preference", null));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}