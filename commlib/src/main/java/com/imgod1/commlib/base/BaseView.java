package com.imgod1.commlib.base;


/**
 * BaseView.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:24
 * @update gaokang 2019/7/2 16:24
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public interface BaseView {
    void showLoading();

    void hideLoading();

    void onError(String code, String msg);

}
