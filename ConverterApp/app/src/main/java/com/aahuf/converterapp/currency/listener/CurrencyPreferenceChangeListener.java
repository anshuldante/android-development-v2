package com.aahuf.converterapp.currency.listener;

import android.content.SharedPreferences;
import android.util.Log;

import static com.aahuf.converterapp.currency.CurrencyConstants.DECIMAL_PLACES_PREFERENCE;
import static com.aahuf.converterapp.currency.CurrencyConstants.DEFAULT_CURRENCY_PREFERENCE;
import static com.aahuf.converterapp.currency.CurrencyConstants.SELECTED_CURRENCIES_PREFERENCE;

public class CurrencyPreferenceChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final Runnable runnable;

    public CurrencyPreferenceChangeListener(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (DECIMAL_PLACES_PREFERENCE.equals(key) || DEFAULT_CURRENCY_PREFERENCE.equals(key) || SELECTED_CURRENCIES_PREFERENCE.equals(key)) {
            Log.i("Preference Changes: ", "The preference: " + key + " changed, resetting main activity");
            runnable.run();
        }
    }
}
