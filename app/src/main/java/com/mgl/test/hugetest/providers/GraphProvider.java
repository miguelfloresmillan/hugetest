package com.mgl.test.hugetest.providers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mgl.test.hugetest.views.models.ExchangeResultItem;

import java.util.ArrayList;
import java.util.List;

public class GraphProvider {

    private static final String MY_PREFERENCES = "MY_PREFERENCES";
    private static final String DATA_LIST = "DATA_LIST";
    private static GraphProvider instance;
    private List<ExchangeResultItem> dataList;
    private Context context;

    private GraphProvider(Context context) {
        this.context = context;
        loadData();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        dataList = new ArrayList<>();
        String jsonDataList = sharedPreferences.getString(DATA_LIST, "");
        if (jsonDataList != null && !jsonDataList.isEmpty()) {
            dataList = new Gson().fromJson(jsonDataList, new TypeToken<List<ExchangeResultItem>>() {
            }.getType());
        }
    }

    public static GraphProvider getInstance(Context context) {
        if (instance == null) {
            instance = new GraphProvider(context);
        }
        return instance;
    }

    public void addData(List<ExchangeResultItem> data) {
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        dataList.addAll(data);
        saveData();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DATA_LIST, new Gson().toJson(dataList));
        editor.commit();
    }

    public List<ExchangeResultItem> getHistoricExchange() {
        return dataList;
    }
}
