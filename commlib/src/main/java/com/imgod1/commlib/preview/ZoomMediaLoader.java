package com.imgod1.commlib.preview;

/**
 * ZoomMediaLoader.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:44
 * @update gaokang 2019/7/2 16:44
 * @updateDes
 * @include {@link }
 * @used {@link }
 */

public class ZoomMediaLoader {
    private volatile IZoomMediaLoader loader;
    public  static ZoomMediaLoader getInstance(){
        return  Holder.holder;
    }
    private ZoomMediaLoader(){

    }
    private  static  class  Holder{
           static ZoomMediaLoader holder=new ZoomMediaLoader();
    }
    /****
     * 初始化加载图片类
     * @param  loader 自定义
     * **/
    public  void init(IZoomMediaLoader loader){
        this.loader=loader;
    }

    public IZoomMediaLoader getLoader() {
        if (loader==null){
            throw  new  NullPointerException("loader no init");
        }
        return loader;
    }
}
