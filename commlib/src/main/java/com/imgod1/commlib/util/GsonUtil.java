package com.imgod1.commlib.util;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * GsonUtil.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:50
 * @update gaokang 2019/7/2 16:50
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class GsonUtil {

    /**
     * 将json字符串解析成对象
     *
     * @param json
     * @param token
     * @return T  各种对象
     */
    public static <T> T parseJson(String json, TypeToken<T> token) {
        Gson g = new Gson();
        try {
            @SuppressWarnings("unchecked")
            T t = (T) g.fromJson(json, token.getType());
            return t;
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T parseJson(String json, Class<T> cls) {
        if (json == null) {
            return null;
        }
        Gson g = new Gson();
        try {
            T t = (T) g.fromJson(json, cls);
            return t;
        } catch (Exception e) {
            return null;
        }
    }


    public static <T> String toJsonStr(T t) {
        Gson g = new Gson();
        String s = g.toJson(t);
        return s;
    }

    public static <T> T parseGsonFromAssets(Context context, Class<T> cls, String fileName) {

        StringBuilder newstringBuilder = new StringBuilder();
        InputStream inputStream = null;
        try {
            inputStream = context.getResources().getAssets().open(fileName);
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(isr);
            String jsonLine;
            while ((jsonLine = reader.readLine()) != null) {
                newstringBuilder.append(jsonLine);
            }
            reader.close();
            isr.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result =  newstringBuilder .toString();
        Gson gson = new Gson();
        return (T)gson.fromJson(result, cls);
    }

}
