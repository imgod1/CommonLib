package com.imgod1.commlib.common;

import java.util.ArrayList;
import java.util.List;

/**
 * ImageSavaCache.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:34
 * @update gaokang 2019/7/2 16:34
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class ImageSavaCache {


    private List<String> imgCache = new ArrayList<>();//用于存放保存后的图片路径
    private static final ImageSavaCache instance = new ImageSavaCache();


    public static ImageSavaCache getInstance() {
        return instance;
    }

    public List<String> getImgCache() {
        return imgCache;
    }

    public void setImgCache(String path) {//传入保存后的图片绝对路径
        imgCache.add(path);
    }

    public void removeImgCache() {//清空缓存
        imgCache.clear();
    }

}
