package com.mgl.test.hugetest.views.items;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.mgl.test.hugetest.R;
import com.mgl.test.hugetest.utils.adapter.GenericItem;
import com.mgl.test.hugetest.utils.adapter.GenericItemView;
import com.mgl.test.hugetest.views.models.ExchangeResultItem;

public class ExchangeResultView extends FrameLayout implements GenericItemView {

    private ExchangeResultItem item;
    private TextView fromCurrencyText;
    private TextView toCurrencyText;
    private ImageView fromCurrencyFlag;
    private ImageView toCurrencyFlag;

    public ExchangeResultView(Context context) {
        super(context);
        init();
    }

    public ExchangeResultView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExchangeResultView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.item_exchange_view, this);
        initView(view);
    }

    private void initView(View view) {

        fromCurrencyText = (TextView) view.findViewById(R.id.textView_from_currency);
        toCurrencyText = (TextView) view.findViewById(R.id.textView_to_currency);
        fromCurrencyFlag = (ImageView) view.findViewById(R.id.imageView_from_flag);
        toCurrencyFlag = (ImageView) view.findViewById(R.id.imageView_to_flag);
    }

    @Override
    public void bind(GenericItem item) {
        if (item instanceof ExchangeResultItem) {
            this.item = (ExchangeResultItem) item;
            fromCurrencyText.setText(this.item.getFromCurrencyValue().toString());
            toCurrencyText.setText(this.item.getToCurrencyValue().toString());
            fromCurrencyFlag.setImageDrawable(ContextCompat.getDrawable(getContext(), this.item.getFromCurrencyFlag()));
            toCurrencyFlag.setImageDrawable(ContextCompat.getDrawable(getContext(), this.item.getToCurrencyFlag()));
        }
    }
}
