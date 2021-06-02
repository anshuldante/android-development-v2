package com.aahuf.converterapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aahuf.converterapp.currency.adapter.CurrencyItemAdapter;
import com.aahuf.converterapp.currency.data.CurrencyMasterData;
import com.aahuf.converterapp.currency.listener.AmountTextChangeWatcher;
import com.aahuf.converterapp.currency.listener.CurrencyPreferenceChangeListener;
import com.aahuf.converterapp.currency.model.CurrencyModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.aahuf.converterapp.currency.CurrencyConstants.DECIMAL_PLACES_PREFERENCE;
import static com.aahuf.converterapp.currency.CurrencyConstants.DEFAULT_CURRENCY_PREFERENCE;
import static com.aahuf.converterapp.currency.CurrencyConstants.SELECTED_CURRENCIES_PREFERENCE;

public class MainActivity extends AppCompatActivity {
    private CurrencyPreferenceChangeListener preferenceChangeListener;
    private List<CurrencyModel> selectedCurrencies;
    private CurrencyMasterData currencyMasterData;
    private CurrencyItemAdapter currencyItemAdapter;
    private SharedPreferences preferences;
    private CurrencyModel primaryCurrency;
    private BigDecimal amount;
    private int scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferenceChangeListener = new CurrencyPreferenceChangeListener(this::initCurrencyViews);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener);
        scale = getDecimalPlaces();
        amount = BigDecimal.ONE.setScale(scale, RoundingMode.HALF_UP);

        initCurrencyViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
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

    @Override
    protected void onPause() {
        super.onPause();
        preferences.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        preferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

    private void changeDefaultCurrencyAndUpdateViews(int position) {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(DEFAULT_CURRENCY_PREFERENCE, selectedCurrencies.get(position).getCurrencyName()).apply();
        initCurrencyViews();
    }

    private void initCurrencyViews() {
        currencyMasterData = new CurrencyMasterData(this, this::updateAmountsAndViews, scale);

        initCurrencyModels();
        initCurrencyUiComponents();
    }

    private void initCurrencyModels() {
        Set<String> defaultCurrencies = new HashSet<>(Arrays.asList(getResources().getStringArray(R.array.default_selected_currencies)));
        String defaultPrimaryCurrency = preferences.getString(DEFAULT_CURRENCY_PREFERENCE, getResources().getString(R.string.default_primary_currency));

        final Set<String> currencies = preferences.getStringSet(SELECTED_CURRENCIES_PREFERENCE, defaultCurrencies);
        primaryCurrency = currencyMasterData.getCurrencyModelMap().get(defaultPrimaryCurrency);

        if (primaryCurrency != null) {
            primaryCurrency.setCurrencyAmount(amount);
        }

        selectedCurrencies = currencyMasterData.getCurrencyModelMap().entrySet().stream()
                .filter(entry -> !entry.getKey().equals(defaultPrimaryCurrency)).filter(entry -> currencies.contains(entry.getKey()))
                .map(Map.Entry::getValue).collect(Collectors.toList());
    }

    private void initCurrencyUiComponents() {

        ImageView currencyImage = findViewById(R.id.primaryFlagImage);
        TextView currencySymbol = findViewById(R.id.primaryCurrencySymbol);
        TextView currencyName = findViewById(R.id.primaryCurrencyName);
        TextView currencyLongName = findViewById(R.id.primaryCurrencyLongName);

        EditText currencyAmount = findViewById(R.id.primaryCurrencyAmount);
        currencyAmount.addTextChangedListener(new AmountTextChangeWatcher(this::updateAmountsAndViews, scale));

        currencyImage.setImageResource(primaryCurrency.getFlagImage());
        currencyAmount.setText(primaryCurrency.getCurrencyAmount().toString());
        currencySymbol.setText(primaryCurrency.getCurrencySymbol());
        currencyName.setText(primaryCurrency.getCurrencyName());
        currencyLongName.setText(primaryCurrency.getCurrencyLongName());

        currencyItemAdapter = new CurrencyItemAdapter(selectedCurrencies, this::changeDefaultCurrencyAndUpdateViews);

        RecyclerView currencyRecyclerView = findViewById(R.id.currency_converter_recycler_view);
        currencyRecyclerView.setAdapter(currencyItemAdapter);
    }

    private BigDecimal getConvertedAmount(CurrencyModel currencyModel) {
        BigDecimal amount = null;
        try {
            amount = primaryCurrency.getCurrencyAmount().multiply(currencyModel.getCurrencyRate()).setScale(scale, RoundingMode.HALF_UP)
                    .divide(primaryCurrency.getCurrencyRate(), RoundingMode.HALF_UP).setScale(scale, RoundingMode.HALF_UP);
        } catch (Exception e) {
            Log.e("Unknown Error: ", "Exception while calculating currency amount", e);
        }
        return amount;
    }

    private int getDecimalPlaces() {
        String decimalPreferenceValue = preferences.getString(DECIMAL_PLACES_PREFERENCE, "3");
        try {
            return Integer.parseInt(decimalPreferenceValue);
        } catch (NumberFormatException e) {
            Log.e("Preference Error", "Invalid value for decimal preference" + decimalPreferenceValue);
            return 3;
        }
    }

    private void updateAmountsAndViews(BigDecimal amount) {
        this.amount = amount;
        primaryCurrency.setCurrencyAmount(amount);
        currencyMasterData.getCurrencyModelMap().values().forEach(currencyModel -> currencyModel.setCurrencyAmount(getConvertedAmount(currencyModel)));

        if (currencyItemAdapter != null) {
            currencyItemAdapter.notifyDataSetChanged();
        }
    }

    private void updateAmountsAndViews() {
        currencyMasterData.getCurrencyModelMap().values().forEach(currencyModel -> currencyModel.setCurrencyAmount(getConvertedAmount(currencyModel)));

        if (currencyItemAdapter != null) {
            currencyItemAdapter.notifyDataSetChanged();
        }
    }
}