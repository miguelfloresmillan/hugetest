package com.mgl.test.hugetest;


import com.mgl.test.hugetest.activities.CurrencyExchangeActivity;
import com.mgl.test.hugetest.constants.CurrencyConstants;
import com.mgl.test.hugetest.model.CurrencyConvertModel;
import com.mgl.test.hugetest.model.RateCurrencyModel;
import com.mgl.test.hugetest.presenter.CurrencyExchangePresenter;
import com.mgl.test.hugetest.services.RateConversionService;
import com.mgl.test.hugetest.services.utils.ServiceCallback;
import com.mgl.test.hugetest.views.models.ExchangeResultItem;

import junit.framework.Assert;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
public class CurrencyExchangePresenterTest {

    private CurrencyExchangePresenter presenter;

    private CurrencyConvertModel currencyConvertModel;

    private float amount = 100f;

    private String baseCurrency = CurrencyConstants.USD;

    @Mock
    private RateConversionService rateConversionService;

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

    /**
     * processExchange
     */
    @org.junit.Test
    public void processExchange_assureHideLoadingBar() {

        presenter.processExchange(currencyConvertModel, amount, baseCurrency);
        verify(view).hideLoading();
    }

    @org.junit.Test
    public void processExchange_assureResultItemsWithCorrectConversion() {
        List<ExchangeResultItem> list = presenter.processExchange(currencyConvertModel, amount, baseCurrency);
        assertEquals(4, list.size());
    }

    @org.junit.Test
    public void processExchange_assureHideDialogInvalidResponse() {
        presenter.processExchange(null, amount, baseCurrency);
        verify(view).hideLoading();
    }

    @org.junit.Test
    public void processExchange_assureResultWith1MissingItem() {

        CurrencyConvertModel currencyConvertModel = new CurrencyConvertModel();
        currencyConvertModel.setBase("USD");

        RateCurrencyModel rateCurrencyModel = new RateCurrencyModel();
        rateCurrencyModel.setBrlCurrency(1f);
        rateCurrencyModel.setEurCurrency(1f);
        rateCurrencyModel.setJpyCurrency(1f);

        currencyConvertModel.setRates(rateCurrencyModel);

        List<ExchangeResultItem> list = presenter.processExchange(currencyConvertModel, amount, baseCurrency);
        assertTrue(list != null && !list.isEmpty());
        assertTrue(list.size() == 3);
    }

    @org.junit.Test
    public void processExchange_assureEmptyResponse() {
        CurrencyConvertModel currencyConvertModel = new CurrencyConvertModel();
        currencyConvertModel.setBase("USD");
        currencyConvertModel.setRates(new RateCurrencyModel());
        List<ExchangeResultItem> list = presenter.processExchange(currencyConvertModel, amount, baseCurrency);
        Assert.assertTrue(list.isEmpty());
    }

    @org.junit.Test
    public void processExchange_notCallValuesWhenEmptyValues() {
        CurrencyConvertModel currencyConvertModel = new CurrencyConvertModel();
        currencyConvertModel.setBase("USD");
        currencyConvertModel.setRates(new RateCurrencyModel());
        List<ExchangeResultItem> list = presenter.processExchange(currencyConvertModel, amount, baseCurrency);
        Assert.assertTrue(list.isEmpty());
        verify(view, never()).onExchangeResult(any(List.class), anyString());
    }

    @org.junit.Test
    public void processExchange_assureEmptyResponseWithMessage() {

        CurrencyConvertModel currencyConvertModel = new CurrencyConvertModel();
        currencyConvertModel.setBase("USD");
        currencyConvertModel.setRates(new RateCurrencyModel());
        List<ExchangeResultItem> list = presenter.processExchange(currencyConvertModel, amount, baseCurrency);
        Assert.assertTrue(list.isEmpty());
        verify(view).showMessage(anyString());
    }

    @org.junit.Test
    public void processExchange_assureResponseWithNullValues() {
        List<ExchangeResultItem> list = presenter.processExchange(null, amount, baseCurrency);
        Assert.assertTrue(list.isEmpty());
        verify(view).showMessage(anyString());
    }

    @org.junit.Test
    public void processExchange_assureItemCorrectValues() {
        CurrencyConvertModel currencyConvertModel = new CurrencyConvertModel();
        currencyConvertModel.setBase("USD");

        RateCurrencyModel rateCurrencyModel = new RateCurrencyModel();
        rateCurrencyModel.setBrlCurrency(2f);
        currencyConvertModel.setRates(rateCurrencyModel);

        List<ExchangeResultItem> list = presenter.processExchange(currencyConvertModel, amount, baseCurrency);

        assertTrue(list.get(0).getFromCurrencyFlag() == R.drawable.usa_flag);
        assertTrue(list.get(0).getFromCurrencyValue().equals(100f));
        assertTrue(list.get(0).getToCurrencyFlag() == R.drawable.brasil_flag);
        assertTrue(list.get(0).getToCurrencyValue().equals(200f));

    }

    /**
     * createResultItem
     */
    @org.junit.Test
    public void createResultItem_correctResult() {
        ExchangeResultItem result = presenter.createResultItem(1f, CurrencyConstants.USD, 40f, CurrencyConstants.JPY);
        assertThat(result.getFromCurrencyValue(), Is.is(1f));
        assertThat(result.getToCurrencyValue(), Is.is(40f));
        assertThat(result.getFromCurrencyFlag(), Is.is(R.drawable.usa_flag));
        assertThat(result.getToCurrencyFlag(), Is.is(R.drawable.japan_flag));
    }

    @org.junit.Test
    public void createResultItem_correctDecimalPlaces() {
        ExchangeResultItem result = presenter.createResultItem(1f, CurrencyConstants.USD, 1.455f, CurrencyConstants.JPY);
        assertThat(result.getToCurrencyValue(), Is.is(1.46f));

        result = presenter.createResultItem(1f, CurrencyConstants.USD, 1.454f, CurrencyConstants.JPY);
        assertThat(result.getToCurrencyValue(), Is.is(1.45f));

        result = presenter.createResultItem(1.355f, CurrencyConstants.USD, 1f, CurrencyConstants.JPY);
        assertThat(result.getFromCurrencyValue(), Is.is(1.36f));

        result = presenter.createResultItem(1.345f, CurrencyConstants.USD, 1f, CurrencyConstants.JPY);
        assertThat(result.getFromCurrencyValue(), Is.is(1.35f));
    }

    /**
     * calculateExchange
     */

    @org.junit.Test
    public void calculateExchange_correctResult() {
        Float result = presenter.calculateExchange(2, 3f);
        assertThat(result, Is.is(6f));

        result = presenter.calculateExchange(2, 0f);
        assertThat(result, Is.is(0f));

        result = presenter.calculateExchange(-1, 3f);
        assertThat(result, Is.is(0f));

        result = presenter.calculateExchange(1, -1f);
        assertThat(result, Is.is(0f));
    }
}
