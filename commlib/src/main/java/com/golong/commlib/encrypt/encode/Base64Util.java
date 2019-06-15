package com.golong.commlib.encrypt.encode;
import com.golong.commlib.encrypt.base.Base64;
import com.golong.commlib.encrypt.base.CloseUtils;
import com.golong.commlib.encrypt.base.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Base64 工具类
 */
public class Base64Util {
    /**
     * Base64加密
     *
     * @param string 加密字符串
     * @return 加密结果字符串
     */
    public static String base64EncodeStr(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        return Base64.encodeToString(string.getBytes(), Base64.DEFAULT);
    }

    /**
     * Base64解密
     *
     * @param string 解密字符串
     * @return 解密结果字符串
     */
    public static String base64DecodedStr(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        return new String(Base64.decode(string, Base64.DEFAULT));
    }

    /**
     * Base64加密
     *
     * @param file 加密文件
     * @return 加密结果字符串
     */
    public static String base64EncodeFile(File file) {
        if (null == file) {
            return "";
        }

        FileInputStream inputFile = null;
        try {
            inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            return Base64.encodeToString(buffer, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.close(inputFile);
        }
        return "";
    }

    /**
     * Base64解密
     *
     * @param filePath 解密文件路径
     * @param code     解密文件编码
     * @return 解密结果文件
     */
    public static File base64DecodedFile(String filePath, String code) {
        if (TextUtils.isEmpty(filePath) || TextUtils.isEmpty(code)) {
            return null;
        }

        FileOutputStream fos = null;
        File desFile = new File(filePath);
        try {
            byte[] decodeBytes = Base64.decode(code.getBytes(), Base64.DEFAULT);
            fos = new FileOutputStream(desFile);
            fos.write(decodeBytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseUtils.close(fos);
        }
        return desFile;
    }
}
