package com.aahuf.converterapp.currency.listener;

import android.view.View;

import com.aahuf.converterapp.currency.adapter.CurrencyItemAdapter;

import java.util.function.Consumer;

public class CurrencyItemOnClickListener implements View.OnClickListener {

    private final CurrencyItemAdapter.CurrencyItemViewHolder currencyItemViewHolder;
    private final Consumer<Integer> consumer;

    public CurrencyItemOnClickListener(CurrencyItemAdapter.CurrencyItemViewHolder currencyItemViewHolder, Consumer<Integer> consumer) {
        this.currencyItemViewHolder = currencyItemViewHolder;
        this.consumer = consumer;
    }

    @Override
    public void onClick(View view) {
        consumer.accept(currencyItemViewHolder.getLayoutPosition());
    }
}
