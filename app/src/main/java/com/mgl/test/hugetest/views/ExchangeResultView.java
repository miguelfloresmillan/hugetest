package com.mgl.test.hugetest.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mgl.test.hugetest.R;
import com.mgl.test.hugetest.utils.adapter.GenericItem;
import com.mgl.test.hugetest.utils.adapter.GenericItemView;
import com.mgl.test.hugetest.views.items.ExchangeResultItem;

public class ExchangeResultView extends FrameLayout implements GenericItemView {

    private ExchangeResultItem item;
    private TextView nameText;
    private TextView valueText;

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
        nameText = (TextView) view.findViewById(R.id.textView);
        valueText = (TextView) view.findViewById(R.id.textView_value);
    }

    @Override
    public void bind(GenericItem item) {
        if (item instanceof ExchangeResultItem) {
            this.item = (ExchangeResultItem) item;
            nameText.setText(this.item.getCurrencyName());
            valueText.setText(this.item.getValue().toString());
        }
    }
}
