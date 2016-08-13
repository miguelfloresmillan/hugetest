package com.mgl.test.hugetest.presenter;

import com.mgl.test.hugetest.constants.CurrencyConstants;
import com.mgl.test.hugetest.model.CurrencyConvertModel;
import com.mgl.test.hugetest.services.RateConversionService;
import com.mgl.test.hugetest.services.utils.ServiceCallback;
import com.mgl.test.hugetest.views.items.ExchangeResultItem;

import java.util.ArrayList;
import java.util.List;

public class CurrencyExchangePresenter {

    private CurrencyExchangeView view;

    public CurrencyExchangePresenter(CurrencyExchangeView view) {
        this.view = view;
    }

    public void calculateExchangeRate(final float amount, final String baseCurrency) {
        RateConversionService service = new RateConversionService();
        view.showLoading();
        service.getCurrencyExchanges(baseCurrency, new ServiceCallback<CurrencyConvertModel>() {
            @Override
            public void onResponse(CurrencyConvertModel response) {
                processExchange(response, amount, baseCurrency);
            }

            @Override
            public void onFailure(Exception e) {
                view.hideLoading();
                view.showError(e.getMessage());
            }
        });

    }

    public List<ExchangeResultItem> processExchange(CurrencyConvertModel response, float amount, String baseCurrency) {

        List<ExchangeResultItem> exchangeList = new ArrayList<>();

        if (response.hasCurrency(CurrencyConstants.GBP)) {
            Float val = calculateExchange(amount, response.getGBP());
            exchangeList.add(new ExchangeResultItem(CurrencyConstants.GBP, val));
        }

        if (response.hasCurrency(CurrencyConstants.EUR)) {
            Float val = calculateExchange(amount, response.getEUR());
            exchangeList.add(new ExchangeResultItem(CurrencyConstants.EUR, val));
        }

        if (response.hasCurrency(CurrencyConstants.JPY)) {
            Float val = calculateExchange(amount, response.getJPY());
            exchangeList.add(new ExchangeResultItem(CurrencyConstants.JPY, val));
        }

        if (response.hasCurrency(CurrencyConstants.BRL)) {
            Float val = calculateExchange(amount, response.getBRL());
            exchangeList.add(new ExchangeResultItem(CurrencyConstants.BRL, val));
        }

        view.onExchangeResult(exchangeList, response.getDate());
        view.hideLoading();

        return exchangeList;
    }

    public Float calculateExchange(float amount, Float conversionRate) {
        return amount * conversionRate;
    }

    public interface CurrencyExchangeView {
        void onExchangeResult(List<ExchangeResultItem> hashResult, String date);

        void hideLoading();

        void showLoading();

        void showError(String message);
    }
}
