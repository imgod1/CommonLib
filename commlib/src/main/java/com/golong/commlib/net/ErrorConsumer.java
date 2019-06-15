package com.golong.commlib.net;

import com.golong.commlib.base.BaseView;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonParseException;
import com.golong.commlib.common.ServerConfig;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.io.NotSerializableException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

/**
 * @author Andy
 * @date 2018/7/4 14:07
 * Desc:    网络请求错误
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
