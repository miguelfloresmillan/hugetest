package com.mgl.test.hugetest.views.items;


import com.mgl.test.hugetest.utils.adapter.GenericItem;

public class ExchangeResultItem implements GenericItem {

    private String currencyName;
    private Float value;

    public ExchangeResultItem(String currencyName, Float value) {
        this.currencyName = currencyName;
        this.value = value;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @Override
    public int getType() {
        return 0;
    }
}
