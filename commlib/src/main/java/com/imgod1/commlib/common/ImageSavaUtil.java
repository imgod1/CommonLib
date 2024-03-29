package com.imgod1.commlib.common;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import com.imgod1.commlib.util.AppUtil;

import java.io.*;

/**
 * ImageSavaUtil.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:34
 * @update gaokang 2019/7/2 16:34
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class ImageSavaUtil {

    private static  File SDPATH;

    /**
     * 生成文件夹路径
     */

    static {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            SDPATH = Environment.getExternalStorageDirectory();
        }else {

            SDPATH= AppUtil.INSTANCE.getExternalCacheDir();
        }
    }


    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(context.getExternalCacheDir(), context.getPackageName());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".png";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } // 其次把文件插入到系统图库
        try {
            addMediaStore(context,file,file.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        } // 最后通知图库更新 //
    }

    /**
     * 将图片压缩保存到文件夹 *
     *
     * @param bm
     * @param picName
     */

    public static void saveBitmap(Context context, Bitmap bm, String picName) {
        try {
          String  folderName=SDPATH+File.separator+context.getPackageName()+File.separator+"IMG/";
            // 如果没有文件夹就创建一个程序文件夹
            if (!isFileExist(folderName)) {
                File tempf = createSDDir(folderName);
            }
            File f = new File(folderName, picName + ".JPEG");
            Log.e("filepath", f.getAbsolutePath());
            ImageSavaCache.getInstance().setImgCache(f.getAbsolutePath());//缓存保存后的图片路径
            // 如果该文件夹中有同名的文件，就先删除掉原文件
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Log.e("imgfile", "已经保存");
        /*    Uri uri = Uri.fromFile(f);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));*/
        addMediaStore(context,f,folderName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 质量压缩 并返回Bitmap *
     *
     * @param image 要压缩的图片
     * @return 压缩后的图片
     */
    private Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {
            // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();
            // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            // 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;
            // 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        // 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        // 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 质量压缩
     *
     * @param bitmap
     * @param picName
     */
    public static void compressImageByQuality(final Bitmap bitmap, String picName) {
        // 如果没有文件夹就创建一个程序文件夹
        if (!isFileExist("")) {
            try {
                File tempf = createSDDir("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File f = new File(SDPATH, picName + ".JPEG");
        // 如果该文件夹中有同名的文件，就先删除掉原文件
        if (f.exists()) {
            f.delete();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;
        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        // 循环判断如果压缩后图片是否大于200kb,大于继续压缩
        while (baos.toByteArray().length / 1024 > 500) {
            // 重置baos即让下一次的写入覆盖之前的内容 baos.reset();
            // 图片质量每次减少5 options -= 5;
            // 如果图片质量小于10，则将图片的质量压缩到最小值
            if (options < 0) {
                options = 0;
            }
            // 将压缩后的图片保存到baos中 b
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            // 如果图片的质量已降到最低则，不再进行压缩
            if (options == 0) {
                break;
            }
        }
        // 将压缩后的图片保存的本地上指定路径中
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(new File(SDPATH, picName + ".JPEG"));
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件夹 *
     *
     * @param folderName 文件夹名称
     * @return 文件夹路径
     * @throws IOException
     */
    public static File createSDDir(String folderName) throws IOException {
        File dir = new File( folderName);
        if (!dir.exists()){
            dir.mkdirs();
        }
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            System.out.println("createSDDir:" + dir.getAbsolutePath());
            System.out.println("createSDDir:" + dir.mkdir());
        }
        return dir;
    }

    /**
     * 判断改文件是否是一个标准文件 *
     *
     * @param folderName 判断的文件路径
     * @return 判断结果
     */
    public static boolean isFileExist(String folderName) {
        File file = new File(folderName);
        return file.exists();
    }

    /**
     * 删除指定文件 *
     *
     * @param fileName
     */
    public static void delFile(String fileName) {
        File file = new File(SDPATH + fileName);
        if (file.isFile()) {
            file.delete();
        }
        file.exists();
    }

    /**
     * 删除指定文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.exists()) {
            // 判断文件是否存在
            if (file.isFile()) {
                // 判断是否是文件
                file.delete();
                // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) {
                // 否则如果它是一个目录
                File files[] = file.listFiles();
                // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) {
                    // 遍历目录下所有的文件
                    deleteFile(files[i]);
                    // 把每个文件 用这个方法进行迭代
                }
            }
            file.delete();
        } else {
            Log.i("TAG", "文件不存在！");
        }
    }


    /**
     * 判断是否存在该文件 *
     *
     * @param path * 文件路径
     * @return
     */
    public static boolean fileIsExists(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    /**
     * @param context
     * @param targetFile 要保存的照片文件
     * @param path  要保存的照片的路径地址
     */
    public static void addMediaStore(Context context, File targetFile, String path) {
        ContentResolver resolver = context.getContentResolver();
        ContentValues newValues = new ContentValues(5);
        newValues.put(MediaStore.Images.Media.DISPLAY_NAME, targetFile.getName());
        newValues.put(MediaStore.Images.Media.DATA, targetFile.getPath());
        newValues.put(MediaStore.Images.Media.DATE_MODIFIED, System.currentTimeMillis() / 1000);
        newValues.put(MediaStore.Images.Media.SIZE, targetFile.length());
        newValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, newValues);
        scanleAlbum(context,path);
    }

    //遍历相册
    public static void scanleAlbum(Context context,String photoPath) {
        if (photoPath == null) {
            return;
        }
        File f = new File(photoPath);
        if (Build.VERSION.SDK_INT >= 19) {
            MediaScannerConnection.scanFile(context, new String[]{photoPath}, new String[]{"image/jpeg"}, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String path, Uri uri) {
                    XLog.e("---",path);

                }
            });

        } else {
            if (f.isFile()) {
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                intent.setData(Uri.fromFile(f));
                context.sendBroadcast(intent);
            }
        }
    }

}
