package com.mgl.test.hugetest.model;

import com.google.gson.annotations.SerializedName;
import com.mgl.test.hugetest.constants.CurrencyConstants;

public class CurrencyConvertModel {
    @SerializedName("base")
    private String base;
    @SerializedName("date")
    private String date;
    @SerializedName("rates")
    private RateCurrencyModel rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public RateCurrencyModel getRates() {
        return rates;
    }

    public void setRates(RateCurrencyModel rates) {
        this.rates = rates;
    }

    public Float getGBP() {
        return rates.getGbpCurrency();
    }

    public Float getBRL() {
        return rates.getBrlCurrency();
    }

    public Float getEUR() {
        return rates.getEurCurrency();
    }

    public Float getJPY() {
        return rates.getJpyCurrency();
    }

    public Float getUSD() {
        return rates.getUsdCurrency();
    }

    public boolean hasCurrency(String currency) {
        switch (currency) {
            case CurrencyConstants.EUR:
                return getEUR() != null;
            case CurrencyConstants.BRL:
                return getBRL() != null;
            case CurrencyConstants.GBP:
                return getGBP() != null;
            case CurrencyConstants.JPY:
                return getJPY() != null;
            case CurrencyConstants.USD:
                return getUSD() != null;
        }
        return false;
    }
}
