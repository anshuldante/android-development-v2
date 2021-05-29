package com.aahuf.converterapp.currency.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ConversionRatesModel {
    private String updatedDate;
    private final Map<String, BigDecimal> conversionRates = new HashMap<>();

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Map<String, BigDecimal> getConversionRates() {
        return conversionRates;
    }
}
