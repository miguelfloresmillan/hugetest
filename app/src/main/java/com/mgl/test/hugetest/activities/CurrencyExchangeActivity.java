package com.mgl.test.hugetest.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mgl.test.hugetest.R;
import com.mgl.test.hugetest.constants.CurrencyConstants;
import com.mgl.test.hugetest.presenter.CurrencyExchangePresenter;
import com.mgl.test.hugetest.utils.adapter.GenericAdapter;
import com.mgl.test.hugetest.utils.adapter.GenericAdapterFactory;
import com.mgl.test.hugetest.utils.adapter.GenericItemView;
import com.mgl.test.hugetest.views.custom.NormalEditText;
import com.mgl.test.hugetest.views.items.ExchangeResultView;
import com.mgl.test.hugetest.views.models.ExchangeResultItem;

import java.util.List;

public class CurrencyExchangeActivity extends AppCompatActivity implements CurrencyExchangePresenter.CurrencyExchangeView, View.OnClickListener, TextView.OnEditorActionListener, TextWatcher {

    private CurrencyExchangePresenter presenter;
    private Button calculateButton;
    private NormalEditText currencyEditText;
    private TextView resultTextView;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private GenericAdapter genericAdapter;

    Handler handler = new Handler();
    private Runnable executeChange = new Runnable() {
        public void run() {
            calculateCurrencyExchange(currencyEditText.getText().toString());
        }
    };

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
        currencyEditText = (NormalEditText) findViewById(R.id.editText_currency_value);
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
        currencyEditText.addTextChangedListener(this);
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
        genericAdapter.setItems(exchangeList);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        handler.removeCallbacks(executeChange);
        handler.postDelayed(executeChange, 800);

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
