package com.mgl.test.hugetest.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mgl.test.hugetest.R;
import com.mgl.test.hugetest.constants.CurrencyConstants;
import com.mgl.test.hugetest.presenter.CurrencyExchangePresenter;
import com.mgl.test.hugetest.utils.adapter.GenericAdapter;
import com.mgl.test.hugetest.utils.adapter.GenericAdapterFactory;
import com.mgl.test.hugetest.utils.adapter.GenericItemView;
import com.mgl.test.hugetest.views.ExchangeResultView;
import com.mgl.test.hugetest.views.items.ExchangeResultItem;

import java.util.List;

public class CurrencyExchangeActivity extends AppCompatActivity implements CurrencyExchangePresenter.CurrencyExchangeView, View.OnClickListener, TextView.OnEditorActionListener {

    private CurrencyExchangePresenter presenter;
    private Button calculateButton;
    private EditText currencyEditText;
    private TextView resultTextView;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private GenericAdapter genericAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_exchange);
        init();
        initView();
        initListener();
    }

    private void initView() {
        calculateButton = (Button) findViewById(R.id.button_calculate_exchange);
        currencyEditText = (EditText) findViewById(R.id.editText_currency_value);
        resultTextView = (TextView) findViewById(R.id.textView_result);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setVerticalScrollBarEnabled(false);
        recyclerView.setAdapter(genericAdapter);
    }

    private void initListener() {
        calculateButton.setOnClickListener(this);
        currencyEditText.setOnEditorActionListener(this);
    }

    private void init() {
        presenter = new CurrencyExchangePresenter(this);
        genericAdapter = new GenericAdapter(new GenericAdapterFactory() {
            @Override
            public GenericItemView onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ExchangeResultView(getBaseContext());
            }
        });
    }

    @Override
    public void onClick(View view) {

        calculateCurrencyExchange(currencyEditText.getText().toString());
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            calculateCurrencyExchange(textView.getText().toString());
        }

        return false;
    }

    private void calculateCurrencyExchange(String valueToConvert) {
        if (TextUtils.isEmpty(valueToConvert)) {
            return;
        }
        float value = Float.valueOf(valueToConvert);
        presenter.calculateExchangeRate(value, CurrencyConstants.USD);

    }

    @Override
    public void onExchangeResult(List<ExchangeResultItem> exchangeList, String date) {
        resultTextView.setText("Results for " + date);
        genericAdapter.setItems(exchangeList);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
