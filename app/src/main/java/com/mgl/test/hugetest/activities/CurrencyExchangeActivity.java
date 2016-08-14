package com.mgl.test.hugetest.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mgl.test.hugetest.R;
import com.mgl.test.hugetest.adapters.ViewPagerAdapter;

public class CurrencyExchangeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {


    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private Button buttonExchange;
    private Button buttonGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_exchange);
        init();
        initView();
        initListeners();
    }

    private void init() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    }

    private void initListeners() {
        viewPager.addOnPageChangeListener(this);
        buttonExchange.setOnClickListener(this);
        buttonGraph.setOnClickListener(this);
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        buttonExchange = (Button) findViewById(R.id.button_convert);
        buttonGraph = (Button) findViewById(R.id.button_graph);
        initViewPager();
    }

    private void initViewPager() {
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 1) {
            viewPagerAdapter.loadGraphData();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_convert:
                viewPager.setCurrentItem(0);
                break;
            case R.id.button_graph:
                viewPager.setCurrentItem(1);
                break;

        }
    }
}
