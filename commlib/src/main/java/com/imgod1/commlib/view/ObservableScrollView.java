package com.imgod1.commlib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * ObservableScrollView.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:55
 * @update gaokang 2019/7/2 16:55
 * @updateDes
 * @include {@link }
 * @used {@link }
 */

public class ObservableScrollView extends ScrollView {

    private int downX;
    private int downY;
    private int mTouchSlop;
    private float xDistance;
    private float yDistance;
    private float xLast;
    private float yLast;


    public interface ScrollViewListener {

        void onScrollChanged(ObservableScrollView scrollView, int x, int y,
                             int oldx, int oldy);

    }

    private ScrollViewListener scrollViewListener = null;

    public ObservableScrollView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public ObservableScrollView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                downX = (int) e.getRawX();
//                downY = (int) e.getRawY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int moveY = (int) e.getRawY();
//                int moveX= (int) e.getRawX();
//                if (Math.abs(moveY - downY) > mTouchSlop*2 && Math.abs(moveX-downX)<mTouchSlop*0.8
//                        ) {
////                    &&Math.abs(moveY - downY)>Math.abs(moveX-downX)
//                    return true;
//                }
//                default:
//        }
//        return super.onInterceptTouchEvent(e);

            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;

                /**
                 * X轴滑动距离大于Y轴滑动距离，也就是用户横向滑动时，返回false，ScrollView不处理这次事件，
                 * 让子控件中的TouchEvent去处理，所以横向滑动的事件交由子View处理，
                 * ScrollView只处理纵向滑动事件
                 */
                if (xDistance > yDistance) {
                    return false;
                }

                default:
        }

        return super.onInterceptTouchEvent(ev);

    }

    /**
     * 降低滑动的速度
     */
    @Override
    public void fling(int velocityY) {
        super.fling((int) (velocityY/1.5));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
