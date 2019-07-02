package com.imgod1.commlib.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

/**
 * @author gaokang
 * @date   2018/12/5 16:57
 * Desc:    震动工具类
 */
public class VibratorUtil {

    /**
     * final Activity activity  ：调用该方法的Activity实例
     * long milliseconds ：震动的时长，单位是毫秒
     * long[] pattern  ：自定义震动模式 。数组中数字的含义依次是[静止时长，震动时长，静止时长，震动时长。。。]时长的单位是毫秒
     * boolean isRepeat ： 是否反复震动，如果是true，反复震动，如果是false，只震动一次
     */
    @SuppressLint("MissingPermission")
    public static void vibrate(final Activity activity, long milliseconds) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            VibrationEffect effect = VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE);
            assert vib != null;
            vib.vibrate(effect);
        }else {
            assert vib != null;
            vib.vibrate(milliseconds);

        }
    }
    @SuppressLint("MissingPermission")
    public static void vibrate(final Activity activity, long[] pattern, boolean isRepeat) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(pattern, isRepeat ? 1 : -1);
    }

}
