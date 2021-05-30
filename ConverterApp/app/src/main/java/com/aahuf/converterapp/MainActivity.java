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
import com.aahuf.converterapp.currency.model.CurrencyModel;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private List<CurrencyModel> selectedCurrencies;
    private CurrencyMasterData currencyMasterData;
    CurrencyItemAdapter currencyItemAdapter;
    private SharedPreferences preferences;
    private CurrencyModel primaryCurrency;
    private MathContext mathContext;

    private ImageView currencyImage;
    private EditText currencyAmount;
    private TextView currencySymbol;
    private TextView currencyName;
    private TextView currencyLongName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

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

    private void initCurrencyViews() {
        initCurrencyMasterData();
        initCurrencyModels();
        initCurrencyUiComponents();

        attachChangeListeners();
    }

    private void attachChangeListeners() {
        currencyAmount.addTextChangedListener(new AmountTextChangeWatcher(this::updateAmountsAndViews, mathContext));
    }

    private void initCurrencyModels() {
        Set<String> defaultCurrencies = new HashSet<>(Arrays.asList(getResources().getStringArray(R.array.default_selected_currencies)));
        String defaultPrimaryCurrency = preferences.getString("default_currency_preference", getResources().getString(R.string.default_primary_currency));

        final Set<String> currencies = preferences.getStringSet("selected_currencies_preference", defaultCurrencies);
        primaryCurrency = currencyMasterData.getCurrencyModelMap().get(defaultPrimaryCurrency);

        selectedCurrencies = currencyMasterData.getCurrencyModelMap().entrySet().stream()
                .filter(entry -> !entry.getKey().equals(defaultPrimaryCurrency)).filter(entry -> currencies.contains(entry.getKey()))
                .map(Map.Entry::getValue).collect(Collectors.toList());
    }

    private void initCurrencyMasterData() {
        int decimalPlaces = getDecimalPlaces();
        mathContext = new MathContext(decimalPlaces, RoundingMode.HALF_UP);

        currencyMasterData = getCurrencyData();
    }

    private void initCurrencyUiComponents() {

        currencyImage = findViewById(R.id.primaryFlagImage);
        currencyAmount = findViewById(R.id.primaryCurrencyAmount);
        currencySymbol = findViewById(R.id.primaryCurrencySymbol);
        currencyName = findViewById(R.id.primaryCurrencyName);
        currencyLongName = findViewById(R.id.primaryCurrencyLongName);

        currencyImage.setImageResource(primaryCurrency.getFlagImage());
        currencyAmount.setText(primaryCurrency.getCurrencyAmount().toString());
        currencySymbol.setText(primaryCurrency.getCurrencySymbol());
        currencyName.setText(primaryCurrency.getCurrencyName());
        currencyLongName.setText(primaryCurrency.getCurrencyLongName());

        currencyItemAdapter = new CurrencyItemAdapter(selectedCurrencies);

        RecyclerView currencyRecyclerView = findViewById(R.id.currency_converter_recycler_view);
        currencyRecyclerView.setAdapter(currencyItemAdapter);
    }

    private CurrencyMasterData getCurrencyData() {
        if (currencyMasterData == null) {
            currencyMasterData = new CurrencyMasterData(this, this::updateAmountsAndViews, mathContext);
        }
        return currencyMasterData;
    }

    private BigDecimal getConvertedAmount(CurrencyModel currencyModel) {
        BigDecimal amount = null;
        try {
            amount = primaryCurrency.getCurrencyAmount().multiply(currencyModel.getCurrencyRate(), mathContext).divide(primaryCurrency.getCurrencyRate(), mathContext);
        } catch (Exception e) {
            Log.e("Unknown Error: ", "Exception while calculating currency amount", e);
        }
        return amount;
    }

    private int getDecimalPlaces() {
        String decimalPreferenceValue = preferences.getString("decimal_places_preference", "3");
        try {
            return Integer.parseInt(decimalPreferenceValue);
        } catch (NumberFormatException e) {
            Log.e("Preference Error", "Invalid value for decimal preference" + decimalPreferenceValue);
            return 3;
        }
    }

    private void updateAmountsAndViews(BigDecimal amount) {
        Log.i("Callback reached: ", "method callback to update view reached");

        primaryCurrency.setCurrencyAmount(amount);
        currencyMasterData.getCurrencyModelMap().values().forEach(currencyModel -> currencyModel.setCurrencyAmount(getConvertedAmount(currencyModel)));

        currencyItemAdapter.notifyDataSetChanged();
    }
}