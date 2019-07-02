package com.imgod1.commlib.preview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.imgod1.commlib.R;
import com.imgod1.commlib.common.XLog;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * PrePictureFragment.java
 * 图片预览fragment
 * @author gaokang
 * @version 1.0 2019/7/2 16:43
 * @update gaokang 2019/7/2 16:43
 * @updateDes
 * @include {@link }
 * @used {@link }
 */

public class PrePictureFragment extends Fragment {

    private static final String ITEM_INFO = "ITEM_INFO";
    private static final String IS_CURRENT_INDEX = "IS_CURRENT_INDEX";
    private static final String TAG = "PrePictureFragment";
    private ProgressBar loading;
    private SmoothImageView imageView;
    private View rootView;
    private MySimpleTarget<Bitmap> mySimpleTarget;
    private ImgInfo viewInfo;
    private boolean isCurrentIndex = false;
    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    public static PrePictureFragment getInstance(ImgInfo item, boolean isCurrentIndex) {
        PrePictureFragment fragment = new PrePictureFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ITEM_INFO, item);
        bundle.putBoolean(IS_CURRENT_INDEX, isCurrentIndex);
        fragment.setArguments(bundle);
        return fragment;


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_photo_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();

    }

    @CallSuper
    @Override
    public void onStop() {
        ZoomMediaLoader.getInstance().getLoader().onStop(this);
        super.onStop();
    }


    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ZoomMediaLoader.getInstance().getLoader().clearMemory(mContext);
        release();

    }

    private void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            viewInfo = arguments.getParcelable(ITEM_INFO);
            isCurrentIndex = arguments.getBoolean(IS_CURRENT_INDEX);
            assert viewInfo != null;
            imageView.setThumbRect(viewInfo.getBounds());
            imageView.setTag(viewInfo.getUrl());
            ZoomMediaLoader.getInstance().getLoader().displayImage(this, viewInfo.getUrl(), mySimpleTarget);
            if (!isCurrentIndex) {
                rootView.setBackgroundColor(Color.BLACK);
            } else {
                imageView.setMinimumScale(0.7f);
            }

            imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    if (imageView.checkMinScale()) {
                        assert (getActivity()) != null;
                        ((PrePictureActivity) getActivity()).transformOut();
                    }
                }

                @Override
                public void onOutsidePhotoTap() {
                    if (imageView.checkMinScale()) {
                        assert (getActivity()) != null;
                        ((PrePictureActivity) getActivity()).transformOut();
                    }
                }
            });
            imageView.setOnViewTapListener((view, x, y) -> {
                if (imageView.checkMinScale()) {
                    assert (getActivity()) != null;
                    ((PrePictureActivity) getActivity()).transformOut();
                }
            });

            imageView.setAlphaChangeListener(alpha -> rootView.setBackgroundColor(getColorWithAlpha(alpha / 255f, Color.BLACK)));
            imageView.setTransformOutListener(() -> {
                if (imageView.checkMinScale()) {
                    assert ((PrePictureActivity) getActivity()) != null;
                    ((PrePictureActivity) getActivity()).transformOut();
                }
            });
        }

    }

    private void initView(View view) {
        loading = view.findViewById(R.id.loading);
        imageView = view.findViewById(R.id.photoView);
        rootView = view.findViewById(R.id.rootView);
        rootView.setDrawingCacheEnabled(true);
        imageView.setDrawingCacheEnabled(true);
        mySimpleTarget = new MySimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap bitmap) {
                if (imageView.getTag().toString().equals(viewInfo.getUrl())) {
                    imageView.setImageBitmap(bitmap);
                    loading.setVisibility(View.GONE);
                    XLog.e(TAG,"onResourceReady",System.currentTimeMillis());
                }
            }

            @Override
            public void onLoadFailed(Drawable errorDrawable) {
                loading.setVisibility(View.GONE);
                if (errorDrawable != null) {
                    imageView.setImageDrawable(errorDrawable);
                }
            }

            @Override
            public void onLoadStarted() {
                loading.setVisibility(View.VISIBLE);
                XLog.e(TAG,"onLoadStarted",System.currentTimeMillis());
            }

            @Override
            public void onLoadCancled() {
                loading.setVisibility(View.GONE);

            }
        };
    }

    public void changeBg(int color) {
        rootView.setBackgroundColor(color);
    }

    public static int getColorWithAlpha(float alpha, int baseColor) {
        int a = Math.min(255, Math.max(0, (int) (alpha * 255))) << 24;
        int rgb = 0x00ffffff & baseColor;
        return a + rgb;
    }

    public void transformOut(SmoothImageView.onTransformListener listener) {
        imageView.transformOut(listener);
    }

    public void transformIn() {
        imageView.transformIn(new SmoothImageView.onTransformListener() {
            @Override
            public void onTransformCompleted(SmoothImageView.Status status) {
                rootView.setBackgroundColor(Color.BLACK);
            }
        });
    }

    public void release() {
        mySimpleTarget = null;
        if (imageView != null) {
           /* imageView.setImageBitmap(null);
            imageView.setOnViewTapListener(null);
            imageView.setOnPhotoTapListener(null);
            imageView.setAlphaChangeListener(null);
            imageView.setTransformOutListener(null);
            imageView.transformIn(null);
            imageView.transformOut(null);
            imageView.setOnLongClickListener(null);
             imageView = null;
            rootView = null;*/
            rootView.destroyDrawingCache();
            imageView.destroyDrawingCache();
            isCurrentIndex = false;
        }
    }
}
