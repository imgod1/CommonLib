package com.imgod1.commlib.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author Andy
 * @date 2019/5/16 18:19
 * Desc:
 */
public class TryViewpager extends ViewPager {
    public TryViewpager(@NonNull Context context) {
        super(context);
    }

    public TryViewpager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


  /*  @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }*/

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException  e) {
            e.printStackTrace();
            XLog.e("try",e.getMessage().toString());
            return false;
        }
    }
}
