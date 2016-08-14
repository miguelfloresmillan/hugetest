package com.mgl.test.hugetest.presenter;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.mgl.test.hugetest.R;
import com.mgl.test.hugetest.constants.CurrencyConstants;
import com.mgl.test.hugetest.providers.GraphProvider;
import com.mgl.test.hugetest.views.models.ExchangeResultItem;

import java.util.ArrayList;
import java.util.List;

public class BarGraphPresenter {

    private BarGraphView view;

    public BarGraphPresenter(BarGraphView view) {
        this.view = view;
    }

    public void loadDataForBarGraph() {

        List<ExchangeResultItem> historicExchange = GraphProvider.getInstance(view.getContext()).getHistoricExchange();

        ArrayList<Float> gbpData = loadDataByName(historicExchange, R.drawable.gb_flag);
        BarDataSet barDataSetGBP = loadBarData(gbpData, CurrencyConstants.GBP, Color.rgb(100, 0, 0));

        ArrayList<Float> jpyData = loadDataByName(historicExchange, R.drawable.japan_flag);
        BarDataSet barDataSetJPY = loadBarData(jpyData, CurrencyConstants.JPY, Color.rgb(50, 200, 50));

        ArrayList<Float> brlData = loadDataByName(historicExchange, R.drawable.brasil_flag);
        BarDataSet barDataSetBRL = loadBarData(brlData, CurrencyConstants.BRL, Color.rgb(0, 250, 0));

        ArrayList<Float> eurData = loadDataByName(historicExchange, R.drawable.europe_flag);
        BarDataSet barDataSetEUR = loadBarData(eurData, CurrencyConstants.EUR, Color.rgb(0, 0, 255));

        List<IBarDataSet> barDataSetList = new ArrayList<>();
        barDataSetList.add(barDataSetGBP);
        barDataSetList.add(barDataSetJPY);
        barDataSetList.add(barDataSetBRL);
        barDataSetList.add(barDataSetEUR);

        List<String> labelList = loadXLabels(gbpData.size());

        BarData barData = new BarData(labelList, barDataSetList);

        view.onBarDataLoad(barData);
    }

    public ArrayList<Float> loadDataByName(List<ExchangeResultItem> historicExchange, int currencyType) {
        ArrayList<Float> dataList = new ArrayList<>();
        if (historicExchange != null) {
            for (ExchangeResultItem exchangeResultItem : historicExchange) {
                if (exchangeResultItem.getToCurrencyFlag() == currencyType) {
                    dataList.add(exchangeResultItem.getToCurrencyValue());
                }
            }
        }
        return dataList;
    }

    public List<String> loadXLabels(int numberSaves) {
        List<String> stringList = new ArrayList<>();
        for (int i = 1; i <= numberSaves; i++) {
            stringList.add("Save " + i);
        }
        return stringList;
    }

    public BarDataSet loadBarData(ArrayList<Float> valueList, String labelName, int color) {
        List<BarEntry> entryList = new ArrayList<>();

        int i = 0;
        for (Float value : valueList) {
            entryList.add(new BarEntry(value, i));
            i++;
        }

        BarDataSet barDataSet = new BarDataSet(entryList, labelName);
        barDataSet.setColor(color);

        return barDataSet;
    }

    public interface BarGraphView {

        void onBarDataLoad(BarData barData);

        Context getContext();
    }
}
