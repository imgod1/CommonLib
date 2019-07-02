package com.imgod1.commlib.common;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import com.imgod1.commlib.R;

import java.util.Objects;

/**
 * SmallLoadingView.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:36
 * @update gaokang 2019/7/2 16:36
 * @updateDes
 * @include {@link }
 * @used {@link }
 */

public class SmallLoadingView extends Dialog {

    private static LoadingView mLoadingView;
    public SmallLoadingView(Context context) {
        super(context);

    }

    public SmallLoadingView(Context context, int theme) {
        super(context, theme);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getWindow()).setDimAmount(0.3f);
        }
    }

    public static void showLoading(Activity context){
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
        mLoadingView.setContentView(R.layout.samll_loading_view);
        mLoadingView.setCancelable(false);
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
