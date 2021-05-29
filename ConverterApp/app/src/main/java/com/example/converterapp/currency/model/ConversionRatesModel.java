package com.example.converterapp.currency.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConversionRatesModel {
    private String updatedDate;
    private Map<String, BigDecimal> conversionRates = new HashMap<>();

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Map<String, BigDecimal> getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(Map<String, BigDecimal> conversionRates) {
        this.conversionRates = conversionRates;
    }

    @Override
    public String toString() {
        return "ConversionRatesModel{" +
                "updatedDate='" + updatedDate + '\'' +
                ", conversionRates=" + conversionRates +
                '}';
    }
}
