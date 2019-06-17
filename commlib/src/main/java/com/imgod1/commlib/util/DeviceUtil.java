package com.imgod1.commlib.util;

import android.content.Context;
import android.util.DisplayMetrics;

import java.lang.reflect.Field;

/**
 * @author Andy
 * @date   2019/3/25 15:49
 * @link   {http://blog.csdn.net/andy_l1}
 * Desc:    手机屏幕设备的相关数据，包括宽、高、密度、dpi、状态栏高度
 */
public class DeviceUtil {
    private Context mContext;
    private DisplayMetrics mDm;

    public DeviceUtil(Context context) {
        if(context != null) {
            mContext = context;
            mDm = context.getResources().getDisplayMetrics();
        }
    }

    /**
     * 获取屏幕的宽度
     * @return
     */
    public int getWidth() {
        return mDm != null ? mDm.widthPixels : 0;
    }

    /**
     * 获取屏幕的高度
     * @return
     */
    public int getHeight() {
        return mDm != null ? mDm.heightPixels : 0;
    }

    /**
     * 获取屏幕的密度
     * @return
     */
    public float getDensity() {
        return mDm != null ? mDm.density : 0;
    }

    /**
     * 获取屏幕的dpi
     * @return
     */
    public float getDensityDpi() {
        return mDm != null ? mDm.densityDpi : 0;
    }

    /**
     * 获取状态栏的高度，系统默认高度为25dp
     * @return
     */
    public int getStatusBarHeight(){
        if(mContext == null) return 0;
        int height = (int) ( 25 * getDensity());

        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            Object obj = cls.newInstance();
            Field field = cls.getField("status_bar_height");
            height = mContext.getResources().getDimensionPixelSize(Integer.parseInt(field.get(obj).toString()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return height;
    }
}
