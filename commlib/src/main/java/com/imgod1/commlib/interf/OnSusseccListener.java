package com.imgod1.commlib.interf;

import android.graphics.Bitmap;

/**
 * OnSusseccListener.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:40
 * @update gaokang 2019/7/2 16:40
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public interface OnSusseccListener {
    void onSuccess(boolean isSuccess);
    void onGetBitmap(Bitmap bitmap);
}
