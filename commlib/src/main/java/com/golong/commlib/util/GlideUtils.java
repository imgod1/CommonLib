package com.golong.commlib.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.golong.commlib.R;
import com.golong.commlib.common.GlideApp;
import com.golong.commlib.interf.OnSusseccListener;

/**
 * @author Andy
 * @date 2018/8/22 15:40
 * @link {http://blog.csdn.net/andy_l1}
 * Desc:    GlideUtil.java
 */

public class GlideUtils {


    /**
     * 加载长方形占位图
     */
    public static void displayImage(Context context, Object path, ImageView imageView) {
        GlideApp.with(context)
                .load(path)
                .dontAnimate()
                .error(R.drawable.ic_image_default)
                .placeholder(R.drawable.ic_image_default)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }

    /**
     * 方形占位图
     */
    public static void displaySquareImage(Context context, Object path, ImageView imageView) {
        GlideApp.with(context)
                .load(path)
                .dontAnimate()
                .error(R.drawable.ic_image_default_square)
                .placeholder(R.drawable.ic_image_default_square)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }

    /**
     * 圆形加载
     */
    public static void displayCircleImg(Context mContext, Object path,
                                        ImageView imageview) {
        GlideApp.with(mContext).load(path).centerCrop().apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .placeholder(R.drawable.ic_default_header)
                .error(R.drawable.ic_default_header)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE).skipMemoryCache(false).into(imageview);
    }

    /**
     * 圆角加载长方形占位图
     */
    public static void displayRoundConnerImg(Context mContext, Object path,
                                             ImageView imageview) {
//        GlideApp.with(mContext).load(path).placeholder(R.drawable.ic_holder_img_square)
//                .error(R.drawable.ic_holder_img_square)
//                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(20,
//                        5, RoundedCornersTransformation.CornerType.RESOURCE)))
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageview);

        RoundedCorners roundedCorners = new RoundedCorners(ScreenUtil.dip2px(mContext, 6));
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).placeholder(R.drawable.ic_image_default)
                .error(R.drawable.ic_image_default)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        GlideApp.with(mContext).load(path).apply(options).into(imageview);

    }

    /**
     * 圆角加载正方形占位图
     */
    public static void displayRoundConnerSquareImg(Context mContext, Object path,
                                                   ImageView imageview) {

        RoundedCorners roundedCorners = new RoundedCorners(ScreenUtil.dip2px(mContext, 6));
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).placeholder(R.drawable.ic_image_default_square)
                .error(R.drawable.ic_image_default_square)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        GlideApp.with(mContext).load(path).apply(options).into(imageview);

    }

    /**
     * 默认头像加载
     */
    public static void loadCircleImgDefault(Context mContext, ImageView imageView) {
        GlideApp.with(mContext).load(R.drawable.ic_default_header).centerCrop().apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .placeholder(R.drawable.ic_default_header)
                .error(R.drawable.ic_default_header)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE).skipMemoryCache(false).into(imageView);
    }

    public static void displayGif(Context context, Object path, ImageView imageView) {
        GlideApp.with(context)
                .asGif()
                .load(path)
                .dontAnimate()
                .error(R.drawable.ic_image_default)
                .placeholder(R.drawable.ic_image_default)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }

    public static void displaySuqareGif(Context context, Object path, ImageView imageView) {
        GlideApp.with(context)
                .asGif()
                .load(path)
                .dontAnimate()
                .error(R.drawable.ic_image_default_square)
                .placeholder(R.drawable.ic_image_default_square)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(imageView);

    }

    public static void disPlayViewBackground(Context context, Object path, View view, OnSusseccListener listener) {
        GlideApp.with(context)
                .load(path)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .into(new CustomViewTarget<View, Drawable>(view) {
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        if (listener!=null) {
                            listener.onSuccess(false);
                        }
                    }

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        view.setBackground(resource);
                        if (listener != null) {
                            listener.onSuccess(true);
                        }
                    }

                    @Override
                    protected void onResourceCleared(@Nullable Drawable placeholder) {

                    }
                });
    }


    public static void createBitmap(Context context, Object path, OnSusseccListener listener) {
        final Bitmap[] bitmap = {null};
        GlideApp.with(context)
                .load(path)
                .encodeFormat(Bitmap.CompressFormat.PNG)
                .encodeQuality(80)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        BitmapDrawable bd = (BitmapDrawable) resource;
                        listener.onGetBitmap(bd.getBitmap());
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }
}
