package com.imgod1.commlib.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * BrandUtil.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:27
 * @update gaokang 2019/7/2 16:27
 * @updateDes
 * @include {@link }
 * @used {@link }
 *  判断手机品牌工具类
 */
public class BrandUtil {
    private static final String BRAND_HUAWEI = "HUAWEI";
    private static final String BRAND_XIAOMI = "XIAOMI";
    private static final String BRAND_MEIZU = "MEIZU";
    private static final String BRAND_NONE = "none";
    private static final String MODEL_NONE = "none";

    public static final String MODEL_FRD_AL00 = "FRD-AL00";

    public static boolean isHWPhone() {
        return BRAND_HUAWEI.equalsIgnoreCase(getMobileBrand());
    }

    public static boolean isXiaomi() {
        return getMobileBrand().toUpperCase().contains(BRAND_XIAOMI);
    }

    public static boolean isMeizu() {
        return getMobileBrand().toUpperCase().contains(BRAND_MEIZU);
    }

    /**
     * 获取手机品牌
     */
    public static String getMobileBrand() {
        String manufacturer = Build.MANUFACTURER;
        if (!TextUtils.isEmpty(manufacturer)) {
            return manufacturer.toLowerCase();
        } else {
            return BRAND_NONE;
        }
    }

    @SuppressLint("MissingPermission")
    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    /**
     * 获取手机型号
     * @return
     */
    public static String getMobileModel() {
        String model = Build.MODEL;
        if (!TextUtils.isEmpty(model)) {
            return model;
        } else {
            return MODEL_NONE;
        }
    }

}
