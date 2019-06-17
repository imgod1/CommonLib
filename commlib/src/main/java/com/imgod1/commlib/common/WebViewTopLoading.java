package com.imgod1.commlib.common;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.imgod1.commlib.R;
import com.imgod1.commlib.util.ScreenUtil;

/**
 * @author Andy
 * @date   2018/11/30 16:25
 * @link   {http://blog.csdn.net/andy_l1}
 * Desc:    自定义webview头部加载进度条
 */
public class WebViewTopLoading extends View {

    private Context mContext;
    private int mMax;//进度条最大的进度
    private int mDefaultHeight;//高度
    private int mCurProgress;//当前的进度
    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private int mColor;


    public WebViewTopLoading(Context context) {
        this(context, null);
    }

    public WebViewTopLoading(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebViewTopLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttr(attrs);
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.WebViewTopLoading);
        mMax = array.getInt(R.styleable.WebViewTopLoading_max1, 10);
        mCurProgress = array.getInt(R.styleable.WebViewTopLoading_progress1, 0);
        mDefaultHeight = array.getInt(R.styleable.WebViewTopLoading_progressHeight, 200);
        mColor = array.getColor(R.styleable.WebViewTopLoading_progressColor, mContext.getResources().getColor(R.color.colorPrimary));
        array.recycle();

        mPaint = new Paint();
        mPaint.setColor(mColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //矩形宽度为view的80%
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //mDefaultWidth 为你自定义设置的属性
            mWidth = ScreenUtil.dip2px(mContext, 300);
        } else if (widthMode == MeasureSpec.UNSPECIFIED) {
            mWidth = widthSize;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //mDefaultHeight 为你自定义设置的属性
            mHeight = ScreenUtil.dip2px(mContext, mDefaultHeight);
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float result = mWidth * ((float) mCurProgress / (float) 100);
        canvas.drawRect(0, 0, result, mHeight, mPaint);
    }

    public int getMax() {
        return mMax;
    }

    public void setMax(int max) {
        mMax = max;
    }

    public int getCurProgress() {
        return mCurProgress;
    }

    public interface OnEndListener {
        void onEnd();//动画结束的回调
    }
    public void setCurProgress(int curProgress, long time, final OnEndListener listener) {
        if (mCurProgress == 100) {//重置mCurProgress为0
            mCurProgress = 0;
        }
        //注意是从 mCurProgress->curProgress 来动画来实现
        ValueAnimator animator = ValueAnimator.ofInt(mCurProgress, curProgress);
        animator.setDuration(time);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurProgress = (int) animation.getAnimatedValue();
                postInvalidate();//通知刷新
            }
        });
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (listener != null) {
                    listener.onEnd();
                }
            }
            @Override
            public void onAnimationStart(Animator animation) {
            }
            @Override
            public void onAnimationCancel(Animator animation) {
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    public void setNormalProgress(int newProgress) {
        mCurProgress = 0;
        mCurProgress = newProgress;
        postInvalidate();
    }


}
