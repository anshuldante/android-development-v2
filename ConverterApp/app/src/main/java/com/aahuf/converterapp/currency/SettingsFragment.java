package com.aahuf.converterapp.currency;

import android.os.Bundle;
import android.util.Log;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.aahuf.converterapp.R;


public class SettingsFragment extends PreferenceFragmentCompat {

    Preference selectedCurrenciesPref;
    Preference defaultCurrencyPref;
    Preference decimalPlacesPref;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);


        selectedCurrenciesPref = findPreference("selected_currencies_preference");
        defaultCurrencyPref = findPreference("default_currency_preference");
        decimalPlacesPref = findPreference("decimal_places_preference");

        selectedCurrenciesPref.setOnPreferenceChangeListener(((preference, newValue) -> {
            Log.i("Preferences: ", "Selected Currency Value: " + newValue);
            return true;
        }));

        defaultCurrencyPref.setOnPreferenceChangeListener(((preference, newValue) -> {
            Log.i("Preferences: ", "Default Currency Value: " + newValue);
            return true;
        }));

        decimalPlacesPref.setOnPreferenceChangeListener(((preference, newValue) -> {
            Log.i("Preferences: ", "Decimal Places Value: " + newValue);
            return true;
        }));
    }
}