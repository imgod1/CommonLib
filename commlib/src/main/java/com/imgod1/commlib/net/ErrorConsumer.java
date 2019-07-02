package com.imgod1.commlib.net;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import com.imgod1.commlib.base.BaseView;
import com.imgod1.commlib.common.ServerConfig;
import io.reactivex.functions.Consumer;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import retrofit2.HttpException;

import java.io.NotSerializableException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

/**
 * ErrorConsumer.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:41
 * @update gaokang 2019/7/2 16:41
 * @updateDes
 * @include {@link }
 * @used {@link }
 */

public class ErrorConsumer implements Consumer<Throwable> {
    private BaseView mBaseView;

    public ErrorConsumer(BaseView baseView) {
        this.mBaseView = baseView;
    }

    @Override
    public void accept(Throwable e) throws Exception {
        String ex = "";
        mBaseView.hideLoading();
        if (e instanceof HttpException) {
            if (ServerConfig.isDebug) {
                ex = e.getMessage();
            }
            mBaseView.onError("-1", "服务器无响应!" + ex);
        } else if (e instanceof SocketTimeoutException
                || e instanceof ConnectTimeoutException) {
            if (ServerConfig.isDebug) {
                ex = e.getMessage();
            }
            mBaseView.onError("-1", "网络连接超时!,请重试" + ex);
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            if (ServerConfig.isDebug) {
                ex = e.getMessage();
            }
            mBaseView.onError("-1", "网络连接异常!" + ex);
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            if (ServerConfig.isDebug) {
                ex = e.getMessage();
            }
            mBaseView.onError("-1", "证书验证失败!" + ex);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof JsonSerializer
                || e instanceof NotSerializableException
                || e instanceof ParseException) {
            if (ServerConfig.isDebug) {
                ex = e.getMessage();
            }
            mBaseView.onError("-1", "解析错误!" + ex);
        } else if (e instanceof com.jakewharton.retrofit2.adapter.rxjava2.HttpException) {

            if (ServerConfig.isDebug) {
                ex = e.getMessage();
            }
            mBaseView.onError("-1", "服务器异常,请稍后重试!" + ex);
        } else if (e instanceof IllegalStateException) {
            if (ServerConfig.isDebug) {
                ex = e.getMessage();
            }
            mBaseView.onError("-1", "解析参数异常!" + ex);
        } else {
            if (ServerConfig.isDebug) {
//                mBaseView.onError("-1", "神秘力量导致加载数据失败!");
            }
        }
    }
}
