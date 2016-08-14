package com.mgl.test.hugetest.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.mgl.test.hugetest.R;
import com.mgl.test.hugetest.presenter.BarGraphPresenter;

import java.util.ArrayList;
import java.util.List;

public class ResultGraphFragment extends Fragment implements BarGraphPresenter.BarGraphView {

    private BarChart lineChart;
    private BarGraphPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_graph, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        presenter = new BarGraphPresenter(this);
        initView(view);
        loadData();
    }

    private void initMockData() {
        //Y values
        List<BarEntry> entryList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            entryList.add(new BarEntry(i + 2, i));
        }

        List<BarEntry> entryList2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            entryList2.add(new BarEntry(i + 1.5f, i));
        }

        BarDataSet lineDataSet2 = new BarDataSet(entryList2, "My label2");
        lineDataSet2.setColor(Color.rgb(0, 0, 155));

        BarDataSet lineDataSet = new BarDataSet(entryList, "My label");
        lineDataSet.setColor(Color.rgb(0, 155, 0));

        //x values
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            stringList.add("" + i + 2);
        }


        List<IBarDataSet> barDataSetList = new ArrayList<>();
        barDataSetList.add(lineDataSet2);
        barDataSetList.add(lineDataSet);

        String[] array = new String[]{"1", "2", "3", "4"};

        //LineData lineData = new LineData(stringList, lineDataSet);
        BarData barData = new BarData(stringList, barDataSetList);

    }

    private void initView(View view) {
        lineChart = (BarChart) view.findViewById(R.id.lineChart);
    }

    @Override
    public void onBarDataLoad(BarData barData) {
        lineChart.setData(barData);
        lineChart.setDescription("Exchange Results");
        lineChart.animateXY(2000, 2000);
        lineChart.invalidate();
    }

    public void loadData() {
        if (presenter != null) {
            presenter.loadDataForBarGraph();
        }
    }
}
