package com.imgod1.commlib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Andy
 * @date 2018/9/30 11:06
 * @link {http://blog.csdn.net/andy_l1}
 * Desc:    自定义温度指示条
 */
public class TempView extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    /**
     * 设置温度的最大范围
     */
    private float maxCount = 100f;
    /**
     * 设置当前温度
     */
    private float currentCount = 20f;
    /**
     * 分段颜色
     */
    private static final int[] SECTION_COLORS = {Color.parseColor("#FF1D7F"),Color.parseColor("#DA166B")};
    private Context mContext;

    /**
     * 圆角矩形的高度
     */
    private int mDefaultTempHeight = dipToPx(8);
    private RectF rectProgressBg;
    private LinearGradient shader;


    public TempView(Context context) {
        this(context, null);
    }

    public TempView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public TempView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);

    }

    private void initView(Context context) {
        this.mContext = context;
        //圆角矩形paint
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //确定圆角矩形的范围,在TmepView的最底部,top位置为总高度-圆角矩形的高度
        rectProgressBg = new RectF(0,  0, mWidth, mHeight);
        shader = new LinearGradient(0, 0, mWidth, mHeight, SECTION_COLORS, null, Shader.TileMode.MIRROR);
        mPaint.setShader(shader);
        //绘制圆角矩形 mDefaultTempHeight / 2确定圆角的圆心位置
        canvas.drawRoundRect(rectProgressBg, mDefaultTempHeight / 2, mDefaultTempHeight / 2, mPaint);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.EXACTLY || widthSpecMode == MeasureSpec.AT_MOST) {
            mWidth = widthSpecSize;
        } else {
            mWidth = 0;
        }
        //主要确定view的整体高度,渐变长条的高度+指针的高度+文本的高度+文本与指针的间隙
        if (heightSpecMode == MeasureSpec.AT_MOST || heightSpecMode == MeasureSpec.UNSPECIFIED) {
            mHeight = mDefaultTempHeight;
        } else {
            mHeight = heightSpecSize;
        }
        setMeasuredDimension(mWidth, mHeight);
    }



    private int dipToPx(int dip) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }


    /***
     * 设置最大的温度值
     * @param maxCount
     */
    public void setMaxCount(float maxCount) {
        this.maxCount = maxCount;
    }

    /***
     * 设置当前的温度
     * @param currentCount
     */
    public void setCurrentCount(float currentCount) {
        if (currentCount > maxCount) {
            this.currentCount = maxCount;
        } else if (currentCount < 0f) {
            currentCount = 0f;
        } else {
            this.currentCount = currentCount;
        }
        invalidate();
    }


    public void setTempHeight(int height) {
        this.mDefaultTempHeight = height;
    }

    public float getMaxCount() {
        return maxCount;
    }

    public float getCurrentCount() {
        return currentCount;
    }
}
