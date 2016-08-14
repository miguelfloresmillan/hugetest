package com.mgl.test.hugetest.presenter;

import com.mgl.test.hugetest.R;
import com.mgl.test.hugetest.constants.CurrencyConstants;
import com.mgl.test.hugetest.model.CurrencyConvertModel;
import com.mgl.test.hugetest.services.RateConversionService;
import com.mgl.test.hugetest.services.utils.ServiceCallback;
import com.mgl.test.hugetest.utils.NumberUtils;
import com.mgl.test.hugetest.views.models.ExchangeResultItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrencyExchangePresenter {

    private CurrencyExchangeView view;
    private List<ExchangeResultItem> currentExchange;

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

        if (response == null) {
            view.showMessage("Error Loading Information");
            view.hideLoading();
            return Collections.emptyList();
        }

        List<ExchangeResultItem> exchangeList = new ArrayList<>();

        if (response.hasCurrency(CurrencyConstants.GBP)) {
            Float val = calculateExchange(amount, response.getGBP());
            ExchangeResultItem item = createResultItem(amount, baseCurrency, val, CurrencyConstants.GBP);
            exchangeList.add(item);
        }

        if (response.hasCurrency(CurrencyConstants.EUR)) {
            Float val = calculateExchange(amount, response.getEUR());
            ExchangeResultItem item = createResultItem(amount, baseCurrency, val, CurrencyConstants.EUR);
            exchangeList.add(item);
        }

        if (response.hasCurrency(CurrencyConstants.JPY)) {
            Float val = calculateExchange(amount, response.getJPY());
            ExchangeResultItem item = createResultItem(amount, baseCurrency, val, CurrencyConstants.JPY);
            exchangeList.add(item);
        }

        if (response.hasCurrency(CurrencyConstants.BRL)) {
            Float val = calculateExchange(amount, response.getBRL());
            ExchangeResultItem item = createResultItem(amount, baseCurrency, val, CurrencyConstants.BRL);
            exchangeList.add(item);
        }

        if (exchangeList.isEmpty()) {
            view.showMessage("empty");
        } else {
            this.currentExchange = exchangeList;
            view.onExchangeResult(exchangeList, response.getDate());
        }

        view.hideLoading();

        return exchangeList;
    }

    public ExchangeResultItem createResultItem(float fromAmount, String fromCurrency, Float toAmount, String toCurrency) {
        fromAmount = NumberUtils.formatDecimal(fromAmount, 2);
        toAmount = NumberUtils.formatDecimal(toAmount, 2);
        return new ExchangeResultItem(fromAmount, getFlagFromCurrency(fromCurrency), toAmount, getFlagFromCurrency(toCurrency));
    }

    private int getFlagFromCurrency(String currency) {
        switch (currency) {
            case CurrencyConstants.BRL:
                return R.drawable.brasil_flag;
            case CurrencyConstants.EUR:
                return R.drawable.europe_flag;
            case CurrencyConstants.GBP:
                return R.drawable.gb_flag;
            case CurrencyConstants.USD:
                return R.drawable.usa_flag;
            case CurrencyConstants.JPY:
                return R.drawable.japan_flag;
        }
        return 0;
    }

    public Float calculateExchange(float amount, Float conversionRate) {
        if (amount < 0) {
            amount = 0;
        }
        if (conversionRate < 0) {
            conversionRate = 0f;
        }
        return amount * conversionRate;
    }

    public List<ExchangeResultItem> getCurrentExchange() {
        return currentExchange;
    }

    public void saveCurrentExchange() {

    }

    public interface CurrencyExchangeView {
        void onExchangeResult(List<ExchangeResultItem> hashResult, String date);

        void hideLoading();

        void showLoading();

        void showMessage(String message);

        void showError(String message);
    }
}
