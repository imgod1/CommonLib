package com.golong.commlib.util;


import java.text.DecimalFormat;

/**
 * @author Andy
 * @date   2019/3/25 15:51
 * @link   {http://blog.csdn.net/andy_l1}
 * Desc:    MoneyUtil.java
 */
public class MoneyUtil {
    /**
     * 精确为两位小数
     *
     * @param money
     * @return
     */
    public static <T> String formatMoney(T money) {
        String format = null;
        try {
            Double aDouble = Double.valueOf(String.valueOf(money));
            format = new DecimalFormat("0.00").format(aDouble);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "0.00";
        }
        return format;
    }

    public static <T> String formatIntger(T money) {
        String format = null;
        try {
            Double aDouble = Double.valueOf(String.valueOf(money));
            format = new DecimalFormat("0").format(aDouble);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "0";
        }
        return format;
    }

    /**
     * 奖金额格式化为 1,111.11
     *
     * @param money
     * @return
     */
    public static <T> String formatMoney2(T money) {
        String format = null;
        try {
            Double aDouble = Double.valueOf(String.valueOf(money));
            format = new DecimalFormat("#,##0.00").format(aDouble);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "0.00";
        }
        return format;
    }

}
