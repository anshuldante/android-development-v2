package com.aahuf.converterapp.currency.model;

import java.math.BigDecimal;

public class CurrencyModel {

    private int flagImage;
    private String currencyName;
    private int currencyLongName;
    private String currencySymbol;
    private BigDecimal currencyRate;
    private BigDecimal currencyAmount;

    public int getFlagImage() {
        return flagImage;
    }

    public void setFlagImage(int flagImage) {
        this.flagImage = flagImage;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public int getCurrencyLongName() {
        return currencyLongName;
    }

    public void setCurrencyLongName(int currencyLongName) {
        this.currencyLongName = currencyLongName;
    }

    public BigDecimal getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(BigDecimal currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }
}
