package com.golong.commlib.util;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Andy
 * @date   2019/3/25 15:52
 * @link   {http://blog.csdn.net/andy_l1}
 * Desc:    StringUtil.java
 */

public class StringUtil {

    static final String passwordRegex = "[A-Za-z0-9]{8,20}";

    public static <T> boolean isEmpty(T t) {
        String str;
        if (t instanceof TextView) {
            str = ((TextView) t).getText().toString();
        } else if (t instanceof String) {
            str = (String) t;
        } else {
            return true;
        }

        if (str.trim().equals("") || str.equals("null")) {
            return true;
        }
        return false;
    }

    public static String[] splite(String str) {
        if (isEmpty(str)) {
            return null;
        }
        String[] split = str.split(",");
        return split;
    }

    public static String getValue(String str) {
        if (null == str || str.trim().equals("") || str.equals("null")) {
            return "";
        }
        return str;
    }

    public static boolean isPasswordStrong(String password) {
        return password.matches(passwordRegex);
    }


    /**
     * textview设置其中某几个字体的颜色
     *
     * @param msg   内容
     * @param color 颜色
     * @param start 开的位置
     * @param end   结束的位置
     * @return
     */
    public static SpannableString setSpanStrColor(CharSequence msg, int color, int start, int end) {

        SpannableString spannableString = new SpannableString(msg);
        spannableString.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return spannableString;
    }

    /**
     * textview设置其中某几个字体的大小
     *
     * @param msg   内容
     * @param size  大小
     * @param start 开始的位置
     * @param end   结束的位置
     * @return
     */
    public static SpannableString setSpanStrSize(CharSequence msg, int size, int start, int end) {
        SpannableString spannableString = new SpannableString(msg);
        spannableString.setSpan(new AbsoluteSizeSpan(size), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 字段去除空格
     *
     * @return
     */
    public static String SetString(String str) {
        str = str.trim();
        str = str.replaceAll(" ", "");
        str = str.replaceAll(" +", "");
        str = str.replaceAll(" *", "");//正则表达式  *为0到无穷
        str = str.replaceAll("\\s*", "");
        return str;
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 去掉手机号内除数字外的所有字符
     *
     * @param phoneNum 手机号
     * @return
     */
    public static String formatPhoneNum(String phoneNum) {
        String regex = "(\\+86)|[^0-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNum);
        return matcher.replaceAll("");
    }

    /**
     * 手机号中间四位*号代替
     */
    public static String changePhone(String phone) {
        return phone.substring(0, 3) + "****" + phone.substring(7, 11);
    }


    public static String string2UrlEncode(final String source){

        try {
           return URLEncoder.encode(source,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
    /**
     * 中文转Unicode
     */
    public static String string2Unicode(final String gbString) {   //gbString = "测试"
        char[] utfBytes = gbString.toCharArray();   //utfBytes = [测, 试]
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);   //转换为16进制整型字符串
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }

    /**
     * 中文转Unicode
     */
    public static String unicodeToString(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

    /**
     * 将字符串数组转化为字符串，默认以","分隔
     *
     * @param values 字符串数组
     * @param split  分隔符，默认为","
     * @return 有字符串数组转化后的字符串
     */
    public static String arrayToString(String[] values, String split) {
        StringBuffer buffer = new StringBuffer();
        if (values != null) {
            if (split == null) {
                split = ",";
            }
            int len = values.length;
            for (int i = 0; i < len; i++) {
                buffer.append(values[i]);
                if (i != len - 1) {
                    buffer.append(split);
                }
            }
        }
        return buffer.toString();
    }
    /**
     * string转换为int类型
     */
    public static int getString2Int(String str) {
        if (!str.equals("") && !TextUtils.isEmpty(str)) {
            return Integer.parseInt(str);
        }
        return -1;
    }

    /**
     * string转换为int类型
     */
    public static double getString2Double(String str) {
        if (!str.equals("") && !TextUtils.isEmpty(str)) {
            return Double.valueOf(str);
        }
        return -1;
    }
}
