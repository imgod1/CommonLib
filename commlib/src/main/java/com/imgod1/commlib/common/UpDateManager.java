package com.imgod1.commlib.common;

import android.content.Context;
import android.os.Environment;

import com.imgod1.commlib.view.UpdateDialog;

/**
 * UpDateManager.java
 * 更新管理
 * @author gaokang
 * @version 1.0 2019/7/2 16:35
 * @update gaokang 2019/7/2 16:35
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class UpDateManager {

    public static void downloadAPP(final Context context, String appDownloadUrl, UpdateDialog dialog) {
        final String destFileDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        final String destFileName = appDownloadUrl.substring(appDownloadUrl.lastIndexOf("/") + 1);
       /* OkHttpUtils.get()
                .url(appDownloadUrl)
                .build()
                .execute(new FileCallBack(destFileDir, destFileName) {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(File response, int id) {
                        dialog.dismiss();
                        DownloadUtils.installAPK(context, response);
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                       dialog.setDownLoadProcess(progress*100);
                    }
                });*/
    }

}
