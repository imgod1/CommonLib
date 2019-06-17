package com.imgod1.commlib.payview;

/**
 * 自定义接口，用于给密码输入完成添加回掉事件
 */
public interface OnPasswordInputFinish {
    void inputFinish(String password);
    void findPwd();
}