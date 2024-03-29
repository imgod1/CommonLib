package com.imgod1.commlib.bottombar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.imgod1.commlib.R;

/**
 * MainBottomBarItemView.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:27
 * @update gaokang 2019/7/2 16:27
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class MainBottomBarItemView extends FrameLayout {

    private final View mSelf;
    private final String text;
    private final boolean enable;
    private final Drawable drawable;

    private ImageView mIcon;
    private TextView mText;
    private final ColorStateList mColorStateList;

    public MainBottomBarItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mSelf = LayoutInflater.from(context).inflate(R.layout.view_item_main_bottom_bar, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MainBottomBarItemView);
        text = ta.getString(R.styleable.MainBottomBarItemView_bottomBartext);
        enable = ta.getBoolean(R.styleable.MainBottomBarItemView_bottomBarenable, false);
        drawable = ta.getDrawable(R.styleable.MainBottomBarItemView_bottomBaricon);
        mColorStateList = ta.getColorStateList(R.styleable.MainBottomBarItemView_bottomBarTextColor);
        ta.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mIcon = (ImageView) mSelf.findViewById(R.id.icon);
        mText = (TextView) mSelf.findViewById(R.id.text);

        mIcon.setImageDrawable(drawable);
        mIcon.setEnabled(enable);
        mText.setText(text);
        mText.setEnabled(enable);
        mText.setTextColor(mColorStateList);
    }

    public void setBarEnable(boolean enable){
        mIcon.setEnabled(enable);
        mText.setEnabled(enable);
    }
}
