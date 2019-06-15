package com.golong.commlib.behavior;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author Andy
 * @date 2019/4/25 19:25
 * Desc:
 */
public class SuperTextView extends AppCompatTextView {
    public SuperTextView(Context context) {
        super(context);
    }

    public SuperTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SuperTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
