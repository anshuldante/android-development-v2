package com.aahuf.converterapp.currency.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Consumer;

public class AmountTextChangeWatcher implements TextWatcher {
    private final Consumer<BigDecimal> consumer;
    private final int scale;

    public AmountTextChangeWatcher(Consumer<BigDecimal> consumer, int scale) {
        this.consumer = consumer;
        this.scale = scale;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence value, int start, int before, int count) {
        if (value.length() > 0) {
            try {
                consumer.accept(new BigDecimal(value.toString()).setScale(scale, RoundingMode.HALF_UP));
            } catch (Exception e) {
                Log.e("Amount Listener Error: ", "Error while parsing the : " + value, e);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
