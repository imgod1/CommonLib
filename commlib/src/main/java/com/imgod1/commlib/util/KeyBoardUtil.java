package com.imgod1.commlib.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * KeyBoardUtil.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:51
 * @update gaokang 2019/7/2 16:51
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class KeyBoardUtil {
    /**
     * 打卡软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void openKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     *
     * @param activity
     */
    public static void closeKeybord(Activity activity) {
        final View v = activity.getWindow().peekDecorView();
        if (v != null && v.getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
