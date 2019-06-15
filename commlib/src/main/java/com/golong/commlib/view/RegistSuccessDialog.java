package com.golong.commlib.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.golong.commlib.R;

/**
 * @author Andy
 * @date 2018/10/12 15:47
 * Desc: 注册成功提货券弹窗
 */
public class RegistSuccessDialog extends Dialog {
    private RegistSuccessDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static RegistSuccessDialog creat(Context context,onToMytihuoqClickListener listener) {
         RegistSuccessDialog dialog = new RegistSuccessDialog(context,
                R.style.Theme_Light_NoTitle_Dialog);
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.scale_rising);
        window.setDimAmount(0.7f);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        window.setGravity(Gravity.CENTER);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_regist_success_view, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        ImageView ivExit= view.findViewById(R.id.iv_exit);
        LinearLayout llImag = view.findViewById(R.id.ll_img);
        llImag.setOnClickListener(v -> {
            if (listener!=null){
                listener.onToMytihuoqClick();
                dialog.dismiss();
            }
        });
        ivExit.setOnClickListener(v -> {
            dialog.dismiss();
        });
        return dialog;

    }

    public interface onToMytihuoqClickListener{
        void onToMytihuoqClick();
    }
}
