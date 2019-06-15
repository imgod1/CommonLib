package com.golong.commlib.net;

import com.golong.commlib.base.BaseResponse;
import com.golong.commlib.common.XLog;
import com.google.gson.Gson;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author Andy
 * @date   2019/3/25 14:15
 * @link   {http://blog.csdn.net/andy_l1}
 * Desc:    MyGsonResponseBodyConverter.java
 */
final class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final Type type;

    MyGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }


    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
//            String decode = ResponseDecoder.decode(response);//解密方法
            BaseResponse baseResponse = gson.fromJson(response, BaseResponse.class);
            XLog.e("ResponseBody", response);
//            if (baseResponse.getData() instanceof String){
                if ("0".equals(baseResponse.getCode())||"200".equals(baseResponse.getCode())){
                    return gson.fromJson(response,type);
                }else {
                    String json = "{\"code\":"+baseResponse.getCode()+",\"msg\":\"" + baseResponse.getMsg()+"\",\"data\":null}";
                    return gson.fromJson(json,type);
                }
//            }
//            return gson.fromJson(response, type);
        } catch (Exception e) {
            e.printStackTrace();
            String decode = "{\"code\":-1,\"msg\":\"数据解析异常!\",\"data\":null}";
            return gson.fromJson(decode, type);
        } finally {
            value.close();
        }
    }

}
