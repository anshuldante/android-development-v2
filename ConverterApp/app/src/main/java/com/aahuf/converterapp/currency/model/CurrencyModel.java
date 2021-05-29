package com.aahuf.converterapp.currency.model;

public class CurrencyModel {

    private Integer flagImage;
    private String currencyName;
    private Integer currencyLongName;
    private Double currencyAmount;
    private String currencySymbol;

    public Integer getFlagImage() {
        return flagImage;
    }

    public void setFlagImage(Integer flagImage) {
        this.flagImage = flagImage;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Integer getCurrencyLongName() {
        return currencyLongName;
    }

    public void setCurrencyLongName(Integer currencyLongName) {
        this.currencyLongName = currencyLongName;
    }

    public Double getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(Double currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }
}
