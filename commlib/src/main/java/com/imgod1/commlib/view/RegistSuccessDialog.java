package com.imgod1.commlib.view;

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
import com.imgod1.commlib.R;

/**
 * RegistSuccessDialog.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:56
 * @update gaokang 2019/7/2 16:56
 * @updateDes
 * @include {@link }
 * @used {@link }
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
