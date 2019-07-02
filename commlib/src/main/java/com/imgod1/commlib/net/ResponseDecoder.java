package com.imgod1.commlib.net;

import android.text.TextUtils;

import com.fly.aes.AES256Util;
import com.imgod1.commlib.common.ServerConfig;
import com.imgod1.commlib.util.MD5Util;
import com.imgod1.commlib.util.StringUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * ResponseDecoder.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:41
 * @update gaokang 2019/7/2 16:41
 * @updateDes
 * @include {@link }
 * @used {@link }
 */

public class ResponseDecoder {
    //线上环境
    public static final String KEY = "Yunfanjj2018";

    public static String decode(String response) throws JSONException {
        JSONObject jo = new JSONObject(response);
        boolean secure = jo.optBoolean("Secure", false);
        String secureS = jo.optString("Secure", "");
        int secureI = jo.optInt("Secure");
        if (jo != null && (secure || "true".equals(secureS) || secureI == 1)) {
            if (null != jo.getString("Data")) {
                String jsonObject = jo.getString("Data");
                if (!StringUtil.isEmpty(jsonObject)) {
//                    String decodeData = AESUtil.decode(jsonObject);//解密后的数据
                    String decodeData = AES256Util.decrypt(jsonObject, MD5Util.getMD5(ServerConfig.SECRET_KEY));
                    if (TextUtils.equals("{", decodeData.substring(0, 1))) {

                        JSONObject jsonObject1 = new JSONObject(decodeData);
                        jo.put("Data", jsonObject1);
                    } else if (TextUtils.equals("[", decodeData.substring(0, 1))) {
                        JSONArray jsonArray = new JSONArray(decodeData);
                        jo.put("Data", jsonArray);
                    } else {
                        jo.put("Data", decodeData);
                    }
                    response = jo.toString();
                }
            }
        }
        return response;
    }
}
