package com.mgl.test.hugetest.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
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
import com.mgl.test.hugetest.providers.GraphProvider;
import com.mgl.test.hugetest.utils.adapter.GenericAdapter;
import com.mgl.test.hugetest.utils.adapter.GenericAdapterFactory;
import com.mgl.test.hugetest.utils.adapter.GenericItemView;
import com.mgl.test.hugetest.views.custom.NormalEditText;
import com.mgl.test.hugetest.views.items.ExchangeResultView;
import com.mgl.test.hugetest.views.models.ExchangeResultItem;

import java.util.List;

public class ResultExchangeFragment extends Fragment implements CurrencyExchangePresenter.CurrencyExchangeView, TextView.OnEditorActionListener, TextWatcher, View.OnClickListener {

    private CurrencyExchangePresenter presenter;
    private NormalEditText currencyEditText;
    private TextView resultTextView;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Button buttonSave;

    private GenericAdapter genericAdapter;

    Handler handler = new Handler();
    private Runnable executeChange = new Runnable() {
        public void run() {
            calculateCurrencyExchange(currencyEditText.getText().toString());
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exchange_result, container, false);
        init(view);
        initView(view);
        initListener();
        return view;
    }

    private void init(View view) {
        presenter = new CurrencyExchangePresenter(this);
        genericAdapter = new GenericAdapter(new GenericAdapterFactory() {
            @Override
            public GenericItemView onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ExchangeResultView(getContext());
            }
        });
    }

    private void initView(View view) {
        currencyEditText = (NormalEditText) view.findViewById(R.id.editText_currency_value);
        resultTextView = (TextView) view.findViewById(R.id.textView_result);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        buttonSave = (Button) view.findViewById(R.id.buttonSave);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setVerticalScrollBarEnabled(false);
        recyclerView.setAdapter(genericAdapter);
    }

    private void initListener() {
        currencyEditText.setOnEditorActionListener(this);
        currencyEditText.addTextChangedListener(this);
        buttonSave.setOnClickListener(this);
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
        buttonSave.setEnabled(true);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        buttonSave.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        buttonSave.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View view) {
        GraphProvider.getInstance(getContext()).addData(presenter.getCurrentExchange());
        buttonSave.setEnabled(false);
        showMessage(getString(R.string.copy_saved));
    }
}
