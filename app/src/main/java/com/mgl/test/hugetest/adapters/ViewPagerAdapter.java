package com.mgl.test.hugetest.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mgl.test.hugetest.fragments.ResultExchangeFragment;
import com.mgl.test.hugetest.fragments.ResultGraphFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private ResultExchangeFragment resultExchangeFragment;
    private ResultGraphFragment resultGraphFragment;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        init();
    }

    private void init() {
        resultExchangeFragment = new ResultExchangeFragment();
        resultGraphFragment = new ResultGraphFragment();
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return resultExchangeFragment;
        } else {
            return resultGraphFragment;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }


    public void loadGraphData() {
        if (resultGraphFragment != null) {
            resultGraphFragment.loadData();
        }
    }
}
