package com.imgod1.commlib.common;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.io.File;

/**
 * CompressTask.java
 * 异步压缩
 * @author gaokang
 * @version 1.0 2019/7/2 16:27
 * @update gaokang 2019/7/2 16:27
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class CompressTask extends AsyncTask<File,Void,File> {


    private final ComPressListener mListener;

    public CompressTask(ComPressListener listener) {
        this.mListener=listener;

    }

    @Override
    protected File doInBackground(@NonNull File... files) {
        File compress=null;
        if (files.length==1){
            File file = files[0];
            if (file.length()>2100000){
                compress = ImgUtil.compress(file, 50, 2100000);
            }else {
                compress=files[0];
            }
        }else {
            throw new IllegalArgumentException("ComPressTask is only one execution parameter is allowed");
        }
        return compress;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mListener!=null){
            mListener.onPre();
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (mListener!=null){
            mListener.onResult(file);
        }
    }

    public interface ComPressListener{
        void onPre();
        void onResult(File file);
    }
}
