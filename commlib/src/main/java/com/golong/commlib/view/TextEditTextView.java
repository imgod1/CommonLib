package com.golong.commlib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

/**
 * 拦截键盘向下的 EditTextView
 */
public class TextEditTextView extends android.support.v7.widget.AppCompatEditText {

    private View bar;

    public TextEditTextView(Context context) {
        super(context);
    }

    public TextEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == 1) {
            if (bar != null) {
                bar.setVisibility(GONE);
            }
        }
        return super.onKeyPreIme(keyCode, event);
    }

    public void setBottomBar(View bar) {
        this.bar = bar;
    }
}