package com.golong.commlib.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Snow on 2016/11/11.
 * Description:
 */
public class IntentUtil {
    private IntentUtil() {
    }

    public static void callPhone(Context context, String tel) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + tel);
        intent.setData(data);
        context.startActivity(intent);
    }

    public static void openQQ(Context context) {
//        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Const.QQ_URL)));
    }

    public static void openUrl(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }
}