package com.mgl.test.hugetest;

import com.github.mikephil.charting.data.BarDataSet;
import com.mgl.test.hugetest.fragments.ResultGraphFragment;
import com.mgl.test.hugetest.presenter.BarGraphPresenter;
import com.mgl.test.hugetest.views.models.ExchangeResultItem;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
public class BarGraphPresenterTest {

    private BarGraphPresenter presenter;

    private List<ExchangeResultItem> exchangeResultItemList;

    private ArrayList<Float> floatList;

    @Mock
    private BarDataSet barDataSet;

    @Mock
    private ResultGraphFragment view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        presenter = new BarGraphPresenter(view);

        exchangeResultItemList = new ArrayList<>();
        exchangeResultItemList.add(new ExchangeResultItem(1f, R.drawable.usa_flag, 100f, R.drawable.gb_flag));
        exchangeResultItemList.add(new ExchangeResultItem(1f, R.drawable.usa_flag, 200f, R.drawable.europe_flag));
        exchangeResultItemList.add(new ExchangeResultItem(1f, R.drawable.usa_flag, 300f, R.drawable.brasil_flag));
        exchangeResultItemList.add(new ExchangeResultItem(1f, R.drawable.usa_flag, 400f, R.drawable.japan_flag));

        exchangeResultItemList.add(new ExchangeResultItem(1f, R.drawable.usa_flag, 10f, R.drawable.gb_flag));
        exchangeResultItemList.add(new ExchangeResultItem(1f, R.drawable.usa_flag, 20f, R.drawable.europe_flag));
        exchangeResultItemList.add(new ExchangeResultItem(1f, R.drawable.usa_flag, 30f, R.drawable.brasil_flag));
        exchangeResultItemList.add(new ExchangeResultItem(1f, R.drawable.usa_flag, 40f, R.drawable.japan_flag));

        floatList = new ArrayList<>();
        floatList.add(1f);
        floatList.add(2f);
        floatList.add(3f);
        floatList.add(4f);

    }

    /**
     * loadDataByName
     */

    @Test
    public void loadDataByName_notNullValue() {

        ArrayList<Float> list = presenter.loadDataByName(exchangeResultItemList, R.drawable.gb_flag);
        assertTrue(list != null && !list.isEmpty());

    }

    @Test
    public void loadDataByName_EmptyResult_InvalidFlag() {

        ArrayList<Float> list = presenter.loadDataByName(exchangeResultItemList, -100);
        assertTrue(list.isEmpty());

    }

    @Test
    public void loadDataByName_incorrectInput() {
        ArrayList<Float> list = presenter.loadDataByName(null, R.drawable.gb_flag);
        assertTrue(list.isEmpty());
    }

    @Test
    public void loadDataByName_correctFloatReturns() {

        ArrayList<Float> list = presenter.loadDataByName(exchangeResultItemList, R.drawable.gb_flag);
        assertThat(list.get(0), Is.is(100f));
        assertThat(list.get(1), Is.is(10f));

        list = presenter.loadDataByName(exchangeResultItemList, R.drawable.europe_flag);
        assertThat(list.get(0), Is.is(200f));
        assertThat(list.get(1), Is.is(20f));

        list = presenter.loadDataByName(exchangeResultItemList, R.drawable.brasil_flag);
        assertThat(list.get(0), Is.is(300f));
        assertThat(list.get(1), Is.is(30f));

        list = presenter.loadDataByName(exchangeResultItemList, R.drawable.japan_flag);
        assertThat(list.get(0), Is.is(400f));
        assertThat(list.get(1), Is.is(40f));

    }

    /**
     * loadXLabels
     */
    @Test
    public void loadXLabels_correctSize() {
        List<String> list = presenter.loadXLabels(3);
        assertTrue(list.size() == 3);

        list = presenter.loadXLabels(1);
        assertTrue(list.size() == 1);

        list = presenter.loadXLabels(5);
        assertTrue(list.size() == 5);
    }

    @Test
    public void loadXLabels_validateIncorrectData() {
        List<String> list = presenter.loadXLabels(-1);
        assertTrue(list.isEmpty());

        list = presenter.loadXLabels(0);
        assertTrue(list.isEmpty());
    }

}
