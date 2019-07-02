package com.imgod1.commlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.Gravity;
import com.imgod1.commlib.R;

/**
 * TagTextView.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:56
 * @update gaokang 2019/7/2 16:56
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class TagTextView extends AppCompatTextView {
    private boolean isShow;
    private Drawable drawable;

    public TagTextView(Context context) {
        this(context, null);
    }

    public TagTextView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public TagTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TagTextView, defStyleAttr, 0);
        isShow = typedArray.getBoolean(R.styleable.TagTextView_isTagShow, false);
        drawable = typedArray.getDrawable(R.styleable.TagTextView_tagDrawable);
        typedArray.recycle();
        setText(getText().toString(), BufferType.NORMAL);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (isShow && drawable != null) {
            SpannableString spannableString = new SpannableString("  " + text.toString().trim());
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            ImageSpan imageSpan = new MyImgSpan(drawable, MyImgSpan.ALIGN_MIDDLE);
            spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            setGravity(Gravity.START);
            super.setText(spannableString, type);
        } else {
            super.setText(text.toString().trim(), type);
        }

    }

    public void isShow(boolean show) {
        this.isShow = show;
        setText(getText().toString(), BufferType.NORMAL);
    }

    public void setTagDrawable(@NonNull Drawable d) {
        this.drawable = d;
        setText(getText().toString(), BufferType.NORMAL);
    }

    public boolean isShow() {
        return isShow;
    }

    public Drawable getTagDrawable() {
        return drawable;
    }

    /**
     * {@link "https://raw.githubusercontent.com/Tencent/QMUI_Android/1.x/qmui/src/main/java/com/qmuiteam/qmui/span/QMUIAlignMiddleImageSpan.java"}
     */
    public class MyImgSpan extends ImageSpan {
        public static final int ALIGN_MIDDLE = -100; // 不要和父类重复

        /**
         * 规定这个Span占几个字的宽度
         */
        private float mFontWidthMultiple = -1f;

        /**
         * 是否避免父类修改FontMetrics，如果为 false 则会走父类的逻辑, 会导致FontMetrics被更改
         */
        private boolean mAvoidSuperChangeFontMetrics = false;

        @SuppressWarnings("FieldCanBeLocal")
        private int mWidth;

        /**
         * @param d                 作为 span 的 Drawable
         * @param verticalAlignment 垂直对齐方式, 如果要垂直居中, 则使用 {@link #ALIGN_MIDDLE}
         */
        public MyImgSpan(Drawable d, int verticalAlignment) {
            super(d, verticalAlignment);
        }

        /**
         * @param d                 作为 span 的 Drawable
         * @param verticalAlignment 垂直对齐方式, 如果要垂直居中, 则使用 {@link #ALIGN_MIDDLE}
         * @param fontWidthMultiple 设置这个Span占几个中文字的宽度, 当该值 > 0 时, span 的宽度为该值*一个中文字的宽度; 当该值 <= 0 时, span 的宽度由 {@link #mAvoidSuperChangeFontMetrics} 决定
         */
        public MyImgSpan(Drawable d, int verticalAlignment, float fontWidthMultiple) {
            this(d, verticalAlignment);
            if (fontWidthMultiple >= 0) {
                mFontWidthMultiple = fontWidthMultiple;
            }
        }

        @Override
        public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
            if (mAvoidSuperChangeFontMetrics) {
                Drawable d = getDrawable();
                Rect rect = d.getBounds();
                mWidth = rect.right;
            } else {
                mWidth = super.getSize(paint, text, start, end, fm);
            }
            if (mFontWidthMultiple > 0) {
                mWidth = (int) (paint.measureText("子") * mFontWidthMultiple);
            }
            return mWidth;
        }

        @Override
        public void draw(Canvas canvas, CharSequence text, int start, int end,
                         float x, int top, int y, int bottom, Paint paint) {
            if (mVerticalAlignment == ALIGN_MIDDLE) {
                Drawable d = getDrawable();
                canvas.save();

//            // 注意如果这样实现会有问题：TextView 有 lineSpacing 时，这里 bottom 偏大，导致偏下
//            int transY = bottom - d.getBounds().bottom; // 底对齐
//            transY -= (paint.getFontMetricsInt().bottom - paint.getFontMetricsInt().top) / 2 - d.getBounds().bottom / 2; // 居中对齐
//            canvas.translate(x, transY);
//            d.draw(canvas);
//            canvas.restore();

                Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
                int fontTop = y + fontMetricsInt.top;
                int fontMetricsHeight = fontMetricsInt.bottom - fontMetricsInt.top;
                int iconHeight = d.getBounds().bottom - d.getBounds().top;
                int iconTop = fontTop + (fontMetricsHeight - iconHeight) / 2;
                canvas.translate(x, iconTop);
                d.draw(canvas);
                canvas.restore();
            } else {
                super.draw(canvas, text, start, end, x, top, y, bottom, paint);
            }
        }
    }
}
