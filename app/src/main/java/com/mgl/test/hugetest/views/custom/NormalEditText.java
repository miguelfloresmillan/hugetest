package com.mgl.test.hugetest.views.custom;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

import com.mgl.test.hugetest.utils.manager.FontManager;

public class NormalEditText extends EditText {
    public NormalEditText(Context context) {
        super(context);
        setCustomFont();
    }

    public NormalEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont();
    }

    public NormalEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont();
    }

    public void setCustomFont() {
        setTypeface(FontManager.getInstance().getFont(FontManager.NORMAL_FONT));
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            setCursorVisible(true);
        } else {
            setCursorVisible(false);
        }
    }
}
