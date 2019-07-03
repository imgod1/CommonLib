package com.imgod1.commlib.net;


import android.util.Log;
import com.imgod1.commlib.common.XLog;
import com.imgod1.commlib.user.UserInfoManager;
import com.imgod1.commlib.util.AppUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * NetClient.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:40
 * @update gaokang 2019/7/2 16:40
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public final class NetClient {

    private static final int TIME_OUT = 10 * 1000;


    private static Retrofit mRetrofit;

    public static void initRetrofit(String baseUrl) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
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

        builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                XLog.e("otto", message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY));
        return builder.build();
    }

}
