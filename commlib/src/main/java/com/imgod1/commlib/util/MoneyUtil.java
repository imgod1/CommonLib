package com.imgod1.commlib.util;


import java.text.DecimalFormat;

/**
 * MoneyUtil.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:51
 * @update gaokang 2019/7/2 16:51
 * @updateDes
 * @include {@link }
 * @used {@link }
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
