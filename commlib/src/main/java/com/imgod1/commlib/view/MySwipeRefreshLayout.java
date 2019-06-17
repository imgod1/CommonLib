package com.imgod1.commlib.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by Snow on 2017/6/2.
 * Description:处理滑动冲突
 */

public class MySwipeRefreshLayout extends SwipeRefreshLayout {

    private GestureDetector mGestureDetector;

    public MySwipeRefreshLayout(Context context) {
        super(context);
        init();
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mGestureDetector = new GestureDetector(new YScrollDetecotr());
        setFadingEdgeLength(0);
        this.setColorSchemeColors(Color.parseColor("#FF1D7F"));
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev)
                && mGestureDetector.onTouchEvent(ev);
    }

    class YScrollDetecotr extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            double angle = Math.atan2(Math.abs(distanceY), Math.abs(distanceX));
            if ((180 * angle) / Math.PI < 180) {
                return false;
            }
            return false;
        }
    }
}
