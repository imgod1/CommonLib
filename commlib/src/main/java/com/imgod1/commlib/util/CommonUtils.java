package com.imgod1.commlib.util;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CommonUtils.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:46
 * @update gaokang 2019/7/2 16:46
 * @updateDes
 * @include {@link }
 * @used {@link }
 */

public class CommonUtils {
    public static void hideSoftInputFromWindow(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }

    /**
     * EditText竖直方向是否可以滚动
     *
     * @param editText 需要判断的EditText
     * @return true：可以滚动  false：不可以滚动
     */
    public static boolean canVerticalScroll(EditText editText) {


        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() - editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if (scrollDifference == 0) {
            return false;
        }

        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }

    public static String getCode() {
        return "35" + //we make this look like a valid IMEI
                Build.BOARD.length() % 10 +
                Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 +
                Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 +
                Build.HOST.length() % 10 +
                Build.ID.length() % 10 +
                Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 +
                Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 +
                Build.TYPE.length() % 10 +
                Build.USER.length() % 10; //13 digits
    }

    public static String getCountTimeSeconds(long time) {
        int totalTime = (int) (time / 1000);//秒
        int hour = 0, minute = 0, second = 0;

        if (3600 <= totalTime) {
            hour = totalTime / 3600;
            totalTime = totalTime - 3600 * hour;
        }
        if (60 <= totalTime) {
            minute = totalTime / 60;
            totalTime = totalTime - 60 * minute;
        }
        if (0 <= totalTime) {
            second = totalTime;
        }
        String min= minute>=10? String.valueOf(minute):"0"+minute;
        String sec= second>=10? String.valueOf(second):"0"+second;

            return  min+"分"+sec+"秒";



    }

    // 将字符串转为时间戳
    public static long getTime(String user_time) {
        long re_time = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = Long.parseLong(str.substring(0, 10));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }

    // 将字符串转为时间戳
    public static long getTimeLong(String user_time) {
        long re_time = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = Long.parseLong(str.substring(0, 13));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }


    public static String getCountTimeMinute(long time) {

        int totalTime = (int) (time / 1000);//秒
        int hour = 0, minute = 0, second = 0;

        if (3600 <= totalTime) {
            hour = totalTime / 3600;
            totalTime = totalTime - 3600 * hour;
        }
        if (60 <= totalTime) {
            minute = totalTime / 60;
            totalTime = totalTime - 60 * minute;
        }

        StringBuilder sb = new StringBuilder();


        if (minute < 10) {
            return "0" + minute;
        } else {
            return minute + "";
        }
    }

    public static String getCountTimeSecond(long time) {

        int totalTime = (int) (time / 1000);//秒
        int hour = 0, minute = 0, second = 0;

        if (3600 <= totalTime) {
            hour = totalTime / 3600;
            totalTime = totalTime - 3600 * hour;
        }
        if (60 <= totalTime) {
            minute = totalTime / 60;
            totalTime = totalTime - 60 * minute;
        }
        if (0 <= totalTime) {
            second = totalTime;
        }
        StringBuilder sb = new StringBuilder();
        if (second < 10) {
            return "0" + second;
        } else {
            return second + "";
        }
    }

    public static String getCountTimeLast(long time) {

        int totalTime = (int) (time / 1000);//秒
        int hour = 0, minute = 0, second = 0, day = 0;
        if (3600 <= totalTime) {
            hour = totalTime / 3600;
            totalTime = totalTime - 3600 * hour;
        }
        if (hour >= 24) {
            day = hour / 24;
            hour = hour % 24;
        }
        if (60 <= totalTime) {
            minute = totalTime / 60;
            totalTime = totalTime - 60 * minute;
        }
        if (0 <= totalTime) {
            second = totalTime;
        }
        StringBuilder sb = new StringBuilder();
        if (day != 0) {
            return day + "天" + hour + "小时";
        } else if (hour != 0) {
            return hour + "小时" + minute + "分钟";
        } else if (minute != 0) {
            return minute + "分钟" + second + "秒";
        } else {
            return second + "秒";
        }
    }

    public static String transferTime(String time, String format) {
        SimpleDateFormat format1 = new SimpleDateFormat(format);
        return format1.format(new Date(Long.valueOf(time) * 1000));
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(Date dateDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    public static String saveDecimal(double f) {
        DecimalFormat df = new DecimalFormat("###############0.00");//   16位整数位，两小数位
        String temp = df.format(f);
        return temp;
    }

    public static String saveDecimal(String f) {
        Double d = Double.parseDouble(f);
        DecimalFormat df = new DecimalFormat("###############0.00");//   16位整数位，两小数位
        String temp = df.format(d);
        return temp;
    }

    public static void tranferText(Context mContext, TextView tv, String content, int stylemin, int stylemax) {
        SpannableString styledText = new SpannableString(content);
        styledText.setSpan(new TextAppearanceSpan(mContext, stylemin), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(mContext, stylemax), 1, content.length() - 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(mContext, stylemin), content.length() - 2, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(styledText, TextView.BufferType.SPANNABLE);
    }

    public static void copyText(@NonNull Activity mActivity, CharSequence text){
        if (mActivity.getSystemService(Context.CLIPBOARD_SERVICE) != null) {
            ClipboardManager cmb  = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData label = ClipData.newPlainText("label", text);
            assert cmb != null;
            cmb.setPrimaryClip(label);
            KotlinUtilKt.toast("复制成功");
        } else {
            KotlinUtilKt.toast("复制失败");
        }
    }
}
