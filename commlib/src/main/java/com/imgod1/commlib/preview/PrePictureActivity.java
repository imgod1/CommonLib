package com.imgod1.commlib.preview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.imgod1.commlib.R;
import com.imgod1.commlib.common.TryViewpager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * PrePictureActivity.java
 * 图片预览界面
 * @author gaokang
 * @version 1.0 2019/7/2 16:43
 * @update gaokang 2019/7/2 16:43
 * @updateDes
 * @include {@link }
 * @used {@link }
 */

public class PrePictureActivity extends FragmentActivity {


    private static final String IMG_INFO = "image_message";
    private static final String CURRENT_INDEX = "CURRENT_INDEX";


    private TryViewpager mViewPager;


    private ArrayList<ImgInfo> mImgInfos = new ArrayList<>();
    private int mCurrentIndex;
    private List<PrePictureFragment> mFragments = new ArrayList<>();
    private boolean isTransformOut = false;
    private int tempIndex;
    private TextView tvIndex;
    private TextView tvCount;
    private LinearLayout llCount;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pre_picture);
        initView();
        initData();

    }

    public static void start(Context context,ArrayList<ImgInfo> infos,int i){

        Intent intent = new Intent(context,PrePictureActivity.class);
        intent.putExtra(CURRENT_INDEX,i);
        intent.putParcelableArrayListExtra(IMG_INFO,infos);
        context.startActivity(intent);
    }
    private void initData() {

        mCurrentIndex = getIntent().getIntExtra(CURRENT_INDEX, -1);
        mImgInfos=getIntent().getParcelableArrayListExtra(IMG_INFO);
        int size = mImgInfos.size();
        if (size==0){
            llCount.setVisibility(View.GONE);
        }else {
            llCount.setVisibility(View.VISIBLE);
        }
        tvCount.setText(String.format(Locale.SIMPLIFIED_CHINESE,"/%d", size));
        tvIndex.setText(String.valueOf(mCurrentIndex+1));
        for (int i = 0; i < size; i++) {
            mFragments.add(PrePictureFragment.getInstance(mImgInfos.get(i), mCurrentIndex == tempIndex));
        }

        initViewpager();
    }

    private void initView() {
        mViewPager = findViewById(R.id.viewpager);
        tvIndex = findViewById(R.id.tv_index);
        tvCount = findViewById(R.id.tv_count);
        llCount = findViewById(R.id.ll_count);


        mViewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mViewPager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }else {
                    mViewPager.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                PrePictureFragment fragment = mFragments.get(mCurrentIndex);
                fragment.transformIn();
            }
        });
    }

    private void initViewpager() {
        PhotoPagerAdapter mPhotoPagerAdapter = new PhotoPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPhotoPagerAdapter);
        mViewPager.setCurrentItem(mCurrentIndex);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tempIndex = position;
                tvIndex.setText(String.format(Locale.SIMPLIFIED_CHINESE,"%d", position+1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    /***退出预览的动画***/
    public void transformOut() {
        if (isTransformOut) {
            return;
        }
        isTransformOut = true;
        int currentItem = mViewPager.getCurrentItem();
        if (currentItem < mImgInfos.size()) {
            PrePictureFragment fragment = mFragments.get(currentItem);
            fragment.changeBg(Color.TRANSPARENT);
            fragment.transformOut(new SmoothImageView.onTransformListener() {
                @Override
                public void onTransformCompleted(SmoothImageView.Status status) {
                    PrePictureActivity.this.exit();
                }
            });
        } else {
            exit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        transformOut();
    }


    /**
     * 关闭页面
     */
    private void exit() {
        finish();
        overridePendingTransition(0, 0);
    }

    private class PhotoPagerAdapter extends FragmentPagerAdapter {
        FragmentManager mFragmentManager;

        PhotoPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
            mFragmentManager = supportFragmentManager;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments == null ? 0 : mFragments.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
}
