package com.golong.commlib.common;

import android.content.Context;
import android.os.Environment;

import com.golong.commlib.view.UpdateDialog;

/**
 * @author Andy
 * @date 2018/6/27 13:29
 * Desc:    项目更新管理
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
