package com.imgod1.commlib.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * TryViewpager.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:36
 * @update gaokang 2019/7/2 16:36
 * @updateDes
 * @include {@link }
 * @used {@link }
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
