package com.mgl.test.hugetest.utils.adapter;

import android.view.ViewGroup;

public abstract class GenericAdapterFactory {

    public abstract GenericItemView onCreateViewHolder(ViewGroup parent, int viewType);

}