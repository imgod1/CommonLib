package com.imgod1.commlib.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * NoScrollViewPager.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:55
 * @update gaokang 2019/7/2 16:55
 * @updateDes
 * @include {@link }
 * @used {@link }
 */

public class NoScrollViewPager extends ViewPager {

  private boolean isPagingEnabled = false;
  public NoScrollViewPager(Context context) {
    super(context);
  }

  public NoScrollViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    return this.isPagingEnabled && super.onTouchEvent(event);
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent event) {
    return this.isPagingEnabled && super.onInterceptTouchEvent(event);
  }

  @Override public void scrollTo(int x, int y) {
    super.scrollTo(x, y);
  }
}
