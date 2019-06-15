package com.golong.commlib.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author Andy
 * @date 2019/3/25 15:48
 * @link {http://blog.csdn.net/andy_l1}
 * Desc:    BigDecimalUtil.java
 */
public class BigDecimalUtil {
    private static final int DEF_DIV_SCALE = 10;

    private BigDecimalUtil() {
    }

    /**
     * 加
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double add(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.add(b2).doubleValue();

    }

    /**
     * 减
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double sub(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.subtract(b2).doubleValue();

    }

    /**
     * 乘
     *
     * @param d1
     * @param d2
     * @return
     */
    public static String mul(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        double v = b1.multiply(b2).doubleValue();
        return new DecimalFormat("0.00").format(v);

    }

    /**
     * 除
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double div(double d1, double d2) {

        return div(d1, d2, DEF_DIV_SCALE);

    }

    public static double div(double d1, double d2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * 四舍五如保留两位小数
     */
    public static String format(String value) {
        BigDecimal bigDec = new BigDecimal(value);
        double total = bigDec.setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(total);
    }
}
