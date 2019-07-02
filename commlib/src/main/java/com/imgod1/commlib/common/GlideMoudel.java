package com.imgod1.commlib.common;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

/**
 * GlideMoudel.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:31
 * @update gaokang 2019/7/2 16:31
 * @updateDes
 * @include {@link }
 * @used {@link }
 */

@GlideModule
public class GlideMoudel extends AppGlideModule{

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {

        RequestOptions options = new RequestOptions()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.DATA);
        builder.setDefaultRequestOptions(options);
        super.applyOptions(context, builder);
    }
}
