package com.imgod1.commlib.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * 关于 Toast 提示的业务提醒
 */
public class ToastShow {
    private static ToastShow mToastShow;
    private static Toast toast;
    private Context mContext;
    private static final int SHOW_STR = 10001;
    private static final int SHOW_RES = 10002;

    /**
     * 显示字符串
     *
     * @param str
     */
    public static void showMessage(String str) {
        initHandler();
        Message m = new Message();
        m.what = SHOW_STR;
        m.obj = str;
        handler.sendMessage(m);
    }

    /**
     * 显示资源文本
     *
     * @param res
     */
    public static void showMessage(@StringRes int res) {
        initHandler();
        Message m = new Message();
        m.what = SHOW_RES;
        m.obj = res;
        handler.sendMessage(m);
    }


    private static void initHandler() {
        if (null != handler) {
            return;
        }
        handler = new Handler(CommonLib.getApplicationContext().getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case SHOW_STR:
                        getInstance(CommonLib.getApplicationContext()).show((String) msg.obj);
                        break;
                    case SHOW_RES:
                        getInstance(CommonLib.getApplicationContext()).show((int)
                                msg.obj);
                        break;
                    default:
                        break;
                }


            }
        };
    }

    private static Handler handler;

    private static ToastShow getInstance(Context context) {
        if (mToastShow == null) {
            mToastShow = new ToastShow(context);
        }
        return mToastShow;
    }

    private ToastShow(Context context) {
        this.mContext = context;
    }

    private void show(int string) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(mContext, string, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void show(String string) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(mContext, string, Toast.LENGTH_SHORT);
        toast.show();
    }


}
