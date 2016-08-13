package com.mgl.test.hugetest;


import com.mgl.test.hugetest.activities.CurrencyExchangeActivity;
import com.mgl.test.hugetest.constants.CurrencyConstants;
import com.mgl.test.hugetest.model.CurrencyConvertModel;
import com.mgl.test.hugetest.model.RateCurrencyModel;
import com.mgl.test.hugetest.presenter.CurrencyExchangePresenter;
import com.mgl.test.hugetest.views.items.ExchangeResultItem;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
public class Test {

    private CurrencyExchangePresenter presenter;

    private CurrencyConvertModel currencyConvertModel;

    private float amount = 100f;

    private String baseCurrency = CurrencyConstants.USD;

    @Mock
    private CurrencyExchangeActivity view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        presenter = new CurrencyExchangePresenter(view);

        //init base service response with correct data
        currencyConvertModel = new CurrencyConvertModel();
        currencyConvertModel.setBase("USD");

        RateCurrencyModel rateCurrencyModel = new RateCurrencyModel();

        rateCurrencyModel.setGbpCurrency(1f);
        rateCurrencyModel.setBrlCurrency(1f);
        rateCurrencyModel.setEurCurrency(1f);
        rateCurrencyModel.setJpyCurrency(1f);

        currencyConvertModel.setRates(rateCurrencyModel);

    }

    @org.junit.Test
    public void assureHideLoadingBar() {

        presenter.processExchange(currencyConvertModel, amount, baseCurrency);
        verify(view).hideLoading();
    }

    @org.junit.Test
    public void assureResultItemsWithCorrectConversion() {
        List<ExchangeResultItem> list = presenter.processExchange(currencyConvertModel, amount, baseCurrency);
        assertEquals(4, list.size());
    }

    @org.junit.Test
    public void assureResultWith1MissingItem() {

        CurrencyConvertModel currencyConvertModel = new CurrencyConvertModel();
        currencyConvertModel.setBase("USD");

        RateCurrencyModel rateCurrencyModel = new RateCurrencyModel();
        rateCurrencyModel.setBrlCurrency(1f);
        rateCurrencyModel.setEurCurrency(1f);
        rateCurrencyModel.setJpyCurrency(1f);

        currencyConvertModel.setRates(rateCurrencyModel);

        List<ExchangeResultItem> list = presenter.processExchange(currencyConvertModel, amount, baseCurrency);
        assertTrue(list != null && !list.isEmpty());
    }

    

}
