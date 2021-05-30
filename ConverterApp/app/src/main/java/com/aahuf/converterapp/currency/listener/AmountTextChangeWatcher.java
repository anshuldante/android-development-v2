package com.aahuf.converterapp.currency.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.Consumer;

public class AmountTextChangeWatcher implements TextWatcher {
    private final Consumer<BigDecimal> consumer;
    private final MathContext mathContext;

    public AmountTextChangeWatcher(Consumer<BigDecimal> consumer, MathContext mathContext) {
        this.consumer = consumer;
        this.mathContext = mathContext;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence value, int start, int before, int count) {
        if (value.length() > 0) {
            try {
                consumer.accept(new BigDecimal(value.toString(), mathContext));
            } catch (Exception e) {
                Log.e("Amount Listener Error: ", "Error while parsing the : " + value, e);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
