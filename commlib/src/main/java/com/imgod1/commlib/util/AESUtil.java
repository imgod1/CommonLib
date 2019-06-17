package com.imgod1.commlib.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by win7 on 2016/1/6.
 */
public class AESUtil {

    public static String KEY = "AIYUAPP";

    /**
     * 加密
     *
     * @param stringToEncode
     * @return
     * @throws NullPointerException
     */
    public static String encode(String stringToEncode) throws NullPointerException {
        try {
//            SecretKeySpec keySpec = getKey(md5(KEY));
            SecretKeySpec keySpec = getKey("61C49FCEF09924D92CAFDCC254DF372B");
            byte[] clearText = stringToEncode.getBytes("UTF8");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            String encryptedValue = Base64.encodeToString(cipher.doFinal(clearText), Base64.DEFAULT);
            return encryptedValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 解密
     *
     * @param str
     * @return
     */
    public static String decode(String str) {
        try {
//            SecretKeySpec keySpec = getKey(md5(KEY));
            SecretKeySpec keySpec = getKey("61C49FCEF09924D92CAFDCC254DF372B");
            byte[] clearText = Base64.decode(str.getBytes(), Base64.DEFAULT);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            return new String(cipher.doFinal(clearText), "utf-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static SecretKeySpec getKey(String password) throws UnsupportedEncodingException {
        int keyLength = 256;
        byte[] keyBytes = new byte[keyLength / 8];
        Arrays.fill(keyBytes, (byte) 0x0);
        byte[] passwordBytes = password.getBytes("UTF-8");
        int length = passwordBytes.length < keyBytes.length ? passwordBytes.length : keyBytes.length;
        System.arraycopy(passwordBytes, 0, keyBytes, 0, length);
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        return key;
    }

    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

}
