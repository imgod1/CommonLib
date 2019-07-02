package com.imgod1.commlib.util;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * FileUtil.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:50
 * @update gaokang 2019/7/2 16:50
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class FileUtil {

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();


    public static final String PATH_IMAGE_LOAD = "/zxstudy/imageload";
    public static final String PATH_IMAGE_COMPRESS = "/zxstudy/compress";
    public static final String PATH_IMAGE_PHOTO = "/zxstudy/photo";

    public static String getSystemPhotoPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera";
    }

    public static boolean mkdir(File file) {
        while (!file.getParentFile().exists()) {
            mkdir(file.getParentFile());
        }
        return file.mkdir();
    }

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


    public static String getUsablePath(Context context,String simpath) {
        if (isExternalStorageWritable()) {
            return PATH_SDCARD + simpath;
        } else {
            return context.getFilesDir() + simpath;
        }
    }


    public static byte[] toByteArray(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

}
