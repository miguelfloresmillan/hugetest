package com.mgl.test.hugetest.views.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.mgl.test.hugetest.utils.manager.FontManager;

public class NormalTextView extends TextView {

    public NormalTextView(Context context) {
        super(context);
        setCustomFont();
    }

    public NormalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont();
    }

    public NormalTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont();
    }

    public void setCustomFont() {
        setTypeface(FontManager.getInstance().getFont(FontManager.NORMAL_FONT));
    }
}
