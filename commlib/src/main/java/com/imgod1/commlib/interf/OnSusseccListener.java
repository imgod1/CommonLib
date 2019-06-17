package com.imgod1.commlib.interf;

import android.graphics.Bitmap;

/**
 * @author Andy
 * @date 2019/1/11 10:05
 * Desc:
 */
public interface OnSusseccListener {
    void onSuccess(boolean isSuccess);
    void onGetBitmap(Bitmap bitmap);
}
