package com.example.converterapp.currency.data;

import static com.example.converterapp.currency.CurrencyConstants.CURRENCY_FLAGS;
import static com.example.converterapp.currency.CurrencyConstants.CURRENCY_NAMES;
import static com.example.converterapp.currency.CurrencyConstants.CURRENCY_LONGNAMES;
import static com.example.converterapp.currency.CurrencyConstants.CURRENCY_SYMBOLS;

import com.example.converterapp.currency.model.CurrencyModel;


import java.util.ArrayList;
import java.util.List;

public class CurrencyData {

    public static List<CurrencyModel> getCurrencyModels() {
        List<CurrencyModel> models = new ArrayList<>();
        int length = CURRENCY_FLAGS.length;
        CurrencyModel currencyModel = null;

        for (int i = 0; i < length; i++) {
            currencyModel = new CurrencyModel();
            currencyModel.setCurrencyName(CURRENCY_NAMES[i]);
            currencyModel.setCurrencyLongName(CURRENCY_LONGNAMES[i]);
            currencyModel.setCurrencySymbol(CURRENCY_SYMBOLS[i]);
            currencyModel.setFlagImage(CURRENCY_FLAGS[i]);
            currencyModel.setCurrencyAmount(0.0);

            models.add(currencyModel);
        }

        return models;
    }
}
