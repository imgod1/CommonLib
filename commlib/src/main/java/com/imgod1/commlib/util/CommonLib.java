package com.imgod1.commlib.util;

import android.content.Context;
import com.imgod1.commlib.net.NetClient;

/**
 * CommonLib.java。
 *
 * @author gaokang
 * @date 2019/7/3 10:46
 * @update gaokang 2019/7/3 10:46
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class CommonLib {
    private static Context mContext;

    public static Context getApplicationContext() {
        return mContext;
    }

    public static void initCommonLib(Context context, String baseUrl) {
        mContext = context.getApplicationContext();
        NetClient.initRetrofit(baseUrl);
    }


}
