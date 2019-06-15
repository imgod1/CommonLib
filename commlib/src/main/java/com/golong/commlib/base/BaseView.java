package com.golong.commlib.base;


/**
 * @author Andy
 * @date   2019/3/25 14:02
 * @link   {http://blog.csdn.net/andy_l1}
 * Desc:    BaseView.java
 */
public interface BaseView {
    void showLoading();

    void hideLoading();

    void onError(String code, String msg);

}
