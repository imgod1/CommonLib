package com.imgod1.commlib.net;


import android.util.Log;

import com.imgod1.commlib.common.XLog;
import com.imgod1.commlib.user.UserInfoManager;
import com.imgod1.commlib.util.AppUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.imgod1.commlib.common.ServerConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;


/**
 * @author Andy
 * @date 2019/3/25 14:15
 * @link {http://blog.csdn.net/andy_l1}
 * Desc:    NetClient.java
 */
public final class NetClient {

    private static final int TIME_OUT = 10 * 1000;


    private static final Retrofit mRetrofit;
    /**
     * 新域名替换
     */
    private static final Retrofit newRetrofit;

    static {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(ServerConfig.SERVER_HOST)
                .client(getHttpClient())
                .addConverterFactory(MyGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    static {
        newRetrofit = new Retrofit.Builder()
                .baseUrl(ServerConfig.SERVER_NEW_HOST)
                .client(getHttpClient())
                .addConverterFactory(MyGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    private NetClient() {
    }

    public static <Api> Api getInstance(Class<Api> clazz) {
        return mRetrofit.create(clazz);
    }

    public static <Api> Api getNewInstance(Class<Api> clazz) {
        return newRetrofit.create(clazz);
    }

    private static OkHttpClient getHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        builder.addHeader("Platform", "1");
                        builder.addHeader("VersionCode", AppUtil.getVersionCode());
                        if (UserInfoManager.getInstance().getToken() != null && !UserInfoManager.getInstance().getToken().isEmpty()) {
                            Log.e("Authorization", UserInfoManager.getInstance().getToken());
                            builder.addHeader("Authorization", UserInfoManager.getInstance().getToken());
                        } else {
                            builder.addHeader("Authorization", "");
                        }
                        return chain.proceed(builder.build());
                    }
                });

        if (ServerConfig.isDebug) {
            builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    XLog.e("otto", message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return builder.build();
    }

}
