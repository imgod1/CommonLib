package com.imgod1.commlib.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import com.imgod1.commlib.R;
import com.imgod1.commlib.util.ScreenUtil;

/**
 * DividerItemDecoration.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:28
 * @update gaokang 2019/7/2 16:28
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private final Context mContext;
    private boolean isBig=false;
    /*
     * RecyclerView的布局方向，默认先赋值
     * 为纵向布局
     * RecyclerView 布局可横向，也可纵向
     * 横向和纵向对应的分割想画法不一样
     * */
    private int mOrientation = LinearLayoutManager.VERTICAL ;

    /**
     * item之间分割线的size，默认为1
     */
    private int mItemSize;

    /**
     * 绘制item分割线的画笔，和设置其属性
     * 来绘制个性分割线
     */
    private Paint mPaint ;

    /**
     * 构造方法传入布局方向，不可不传
     */
    public DividerItemDecoration(Context context, int orientation,boolean isBigPadding) {
        this.mOrientation = orientation;
        this.isBig=isBigPadding;
        this.mContext=context;
        if(orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL){
            throw new IllegalArgumentException("请传入正确的参数") ;
        }
        mItemSize = 1;
        mItemSize = (int) TypedValue.applyDimension(mItemSize, TypedValue.COMPLEX_UNIT_DIP, context.getResources().getDisplayMetrics());
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG) ;
        mPaint.setColor(context.getResources().getColor(R.color.divider_color));
        /*设置填充*/
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation == LinearLayoutManager.VERTICAL){
            drawVertical(c,parent) ;
        }else {
            drawHorizontal(c,parent) ;
        }
    }

    /**
     * 绘制纵向 item 分割线
     */
    private void drawVertical(Canvas canvas,RecyclerView parent){
        final int left = parent.getPaddingLeft() ;
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight() ;
        final int childSize = parent.getChildCount() ;
        for(int i = 0 ; i < childSize ; i ++){
            final View child = parent.getChildAt( i ) ;
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin ;
            final int bottom = top + mItemSize ;
            canvas.drawRect(left,top,right,bottom,mPaint);
        }
    }

    /**
     * 绘制横向 item 分割线
     */
    private void drawHorizontal(Canvas canvas,RecyclerView parent){
         int top;
         int bottom;
        if (isBig){
             top = parent.getPaddingTop()+ ScreenUtil.dip2px(mContext,25f);
             bottom = parent.getMeasuredHeight() - parent.getPaddingBottom() - ScreenUtil.dip2px(mContext,110f);
        }else {
             top = parent.getPaddingTop() ;
             bottom = parent.getMeasuredHeight() - parent.getPaddingBottom() ;
        }
        final int childSize = parent.getChildCount() ;
        for(int i = 0 ; i < childSize ; i ++){
            final View child = parent.getChildAt( i ) ;
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin ;
            final int right = left + mItemSize ;
            canvas.drawRect(left,top,right,bottom,mPaint);
        }
    }

    /**
     * 设置item分割线的size
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation == LinearLayoutManager.VERTICAL){
            outRect.set(0,0,0,mItemSize);
        }else {
            outRect.set(0,0,mItemSize,0);
        }
    }
}
