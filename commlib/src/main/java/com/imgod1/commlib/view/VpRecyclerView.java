package com.imgod1.commlib.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * VpRecyclerView.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:57
 * @update gaokang 2019/7/2 16:57
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class VpRecyclerView extends RecyclerView {


    private int downX;
    private int downY;
    private int mTouchSlop;

    public VpRecyclerView(Context context) {
        super(context);
        init(context);
    }


    public VpRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public VpRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        setNestedScrollingEnabled(false);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, expandSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return false;
    }
}
