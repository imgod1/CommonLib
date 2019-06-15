
package com.golong.commlib.util;

import android.text.TextUtils;

import java.security.NoSuchAlgorithmException;

public class MD5Util {

    private MD5Util() {
    }

    /**
     * MD5加密
     *
     * @param strMd5
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getMD5(String strMd5) {
        if (TextUtils.isEmpty(strMd5)) {
            return null;
        }
        String sRet = null;
        try {
            java.security.MessageDigest alga = java.security.MessageDigest
                    .getInstance("MD5");
            alga.update(strMd5.getBytes());
            byte[] digesta = alga.digest();
            sRet = byte2hex(digesta);
        } catch (NoSuchAlgorithmException ex) {
        }
        return sRet;
    }

    public static String byte2hex(byte[] b) // 二进制转字符串
    {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
            if (n < b.length - 1)
                hs = hs + "";
        }
        return hs;
    }

}
