package com.mgl.test.hugetest.views.models;


import com.mgl.test.hugetest.utils.adapter.GenericItem;

public class ExchangeResultItem implements GenericItem {

    private Float fromCurrencyValue;
    private int fromCurrencyFlag;
    private Float toCurrencyValue;
    private int toCurrencyFlag;

    public ExchangeResultItem(Float fromCurrencyValue, int fromCurrencyFlag, Float toCurrencyValue, int toCurrencyFlag) {
        this.fromCurrencyValue = fromCurrencyValue;
        this.fromCurrencyFlag = fromCurrencyFlag;
        this.toCurrencyValue = toCurrencyValue;
        this.toCurrencyFlag = toCurrencyFlag;
    }

    public Float getFromCurrencyValue() {
        return fromCurrencyValue;
    }

    public void setFromCurrencyValue(Float fromCurrencyValue) {
        this.fromCurrencyValue = fromCurrencyValue;
    }

    public int getFromCurrencyFlag() {
        return fromCurrencyFlag;
    }

    public void setFromCurrencyFlag(int fromCurrencyFlag) {
        this.fromCurrencyFlag = fromCurrencyFlag;
    }

    public Float getToCurrencyValue() {
        return toCurrencyValue;
    }

    public void setToCurrencyValue(Float toCurrencyValue) {
        this.toCurrencyValue = toCurrencyValue;
    }

    public int getToCurrencyFlag() {
        return toCurrencyFlag;
    }

    public void setToCurrencyFlag(int toCurrencyFlag) {
        this.toCurrencyFlag = toCurrencyFlag;
    }

    @Override
    public int getType() {
        return 0;
    }
}
