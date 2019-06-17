package com.imgod1.commlib.common;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.imgod1.commlib.R;

import java.util.Objects;

/**
 * @author Andy
 * @date   2018/5/15 20:25
 * @link   {http://blog.csdn.net/andy_l1}
 * Desc:    LoadingView.java
 */

public class LoadingView extends Dialog {

    private static LoadingView mLoadingView;
    public LoadingView(Context context) {
        super(context);

    }

    public LoadingView(Context context, int theme) {
        super(context, theme);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getWindow()).setDimAmount(0.2f);
        }
    }

    public static void showLoading(Activity context, CharSequence charSequence, boolean isCanCancle){
        if (context==null||context.isFinishing()){
            return;
        }
        try {
            if (mLoadingView!=null&&mLoadingView.isShowing()){
                mLoadingView.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mLoadingView=new LoadingView(context, R.style.myDialog);
        mLoadingView.setCanceledOnTouchOutside(false);//设置点击不消失'
        mLoadingView.setContentView(R.layout.loading_view);
        TextView tv= (TextView) mLoadingView.findViewById(R.id.loading_tv);
        if (charSequence==null|| TextUtils.isEmpty(charSequence)){
            tv.setVisibility(View.GONE);
        }else {
            tv.setText(charSequence);
        }
        mLoadingView.setCancelable(isCanCancle);
        mLoadingView.show();
    }
    public static void cancelLoading(){
        try {
            if (mLoadingView!=null&&mLoadingView.isShowing()){
                mLoadingView.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
