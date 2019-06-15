package com.golong.commlib.common;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.io.File;

/**
 * @author Andy
 * @date 2019/5/7 9:59
 * Desc: 异步压缩
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
