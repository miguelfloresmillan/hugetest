package com.mgl.test.hugetest.model;

import com.google.gson.annotations.SerializedName;

public class RateCurrencyModel {

    @SerializedName("GBP")
    private Float gbpCurrency;
    @SerializedName("EUR")
    private Float eurCurrency;
    @SerializedName("JPY")
    private Float jpyCurrency;
    @SerializedName("BRL")
    private Float brlCurrency;
    @SerializedName("USD")
    private Float usdCurrency;

    public Float getGbpCurrency() {
        return gbpCurrency;
    }

    public void setGbpCurrency(Float gbpCurrency) {
        this.gbpCurrency = gbpCurrency;
    }

    public Float getEurCurrency() {
        return eurCurrency;
    }

    public void setEurCurrency(Float eurCurrency) {
        this.eurCurrency = eurCurrency;
    }

    public Float getJpyCurrency() {
        return jpyCurrency;
    }

    public void setJpyCurrency(Float jpyCurrency) {
        this.jpyCurrency = jpyCurrency;
    }

    public Float getBrlCurrency() {
        return brlCurrency;
    }

    public void setBrlCurrency(Float brlCurrency) {
        this.brlCurrency = brlCurrency;
    }

    public Float getUsdCurrency() {
        return usdCurrency;
    }

    public void setUsdCurrency(Float usdCurrency) {
        this.usdCurrency = usdCurrency;
    }
}
